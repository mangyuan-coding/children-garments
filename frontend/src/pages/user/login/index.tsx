import {Alert, Checkbox, message} from 'antd';
import React, {useState} from 'react';
import {history, Link, SelectLang, useModel} from 'umi';
import {getPageQuery} from '@/utils/utils';
import logo from '@/assets/logo.svg';
import {fakeAccountLogin, LoginParamsType} from '@/services/login';
import LoginFrom from './components/Login';
import styles from './style.less';

const {Tab, Username, Password, Submit} = LoginFrom;

const LoginMessage: React.FC<{
  content: string;
}> = ({content}) => (
  <Alert
    style={{
      marginBottom: 24,
    }}
    message={content}
    type="error"
    showIcon
  />
);

/**
 * 此方法会跳转到 redirect 参数所在的位置
 */
const replaceGoto = () => {
  const urlParams = new URL(window.location.href);
  const params = getPageQuery();
  let {redirect} = params as { redirect: string };
  if (redirect) {
    const redirectUrlParams = new URL(redirect);
    if (redirectUrlParams.origin === urlParams.origin) {
      redirect = redirect.substr(urlParams.origin.length);
      if (redirect.match(/^\/.*#/)) {
        redirect = redirect.substr(redirect.indexOf('#') + 1);
      }
    } else {
      window.location.href = '/';
      return;
    }
  }
  history.replace(redirect || '/');
};

const Login: React.FC = () => {
  const [userLoginState, setUserLoginState] = useState<API.LoginStateType>({});
  const [submitting, setSubmitting] = useState(false);

  const {refresh} = useModel('@@initialState');
  const [autoLogin, setAutoLogin] = useState(true);
  const [type, setType] = useState<string>('account');

  const handleSubmit = async (values: LoginParamsType) => {
    setSubmitting(true);
    try {
      // 登录
      const msg = await fakeAccountLogin({...values, type});
      if (msg.status === 'ok') {
        message.success('登陆成功！');
        replaceGoto();
        setTimeout(() => {
          refresh();
        }, 0);
        return;
      }
      // 如果失败去设置用户错误信息
      setUserLoginState(msg);
    } catch (error) {
      message.error('登陆失败，请重试！');
    }
    setSubmitting(false);
  };

  const {status} = userLoginState;

  return (
    <div className={styles.container}>
      <div className={styles.lang}>
        <SelectLang/>
      </div>
      <div className={styles.content}>
        <div className={styles.top}>
          <div className={styles.header}>
            <Link to="/">
              <img alt="logo" className={styles.logo} src={logo}/>
              <span className={styles.title}>妈宝童装</span>
            </Link>
          </div>
          <div className={styles.desc}>献给XX的后台管理系统</div>
        </div>

        <div className={styles.main}>
          <LoginFrom activeKey={type} onTabChange={setType} onSubmit={handleSubmit}>
            <Tab key="account" tab="账户密码登录">
              {status === 'error' && !submitting && (
                <LoginMessage content="账户或密码错误"/>
              )}

              <Username
                name="username"
                placeholder="用户名"
                rules={[
                  {
                    required: true,
                    message: '请输入用户名!',
                  },
                ]}
              />
              <Password
                name="password"
                placeholder="密码"
                rules={[
                  {
                    required: true,
                    message: '请输入密码！',
                  },
                ]}
              />
            </Tab>
            <div>
              <Checkbox checked={autoLogin} onChange={(e) => setAutoLogin(e.target.checked)}>
                自动登录
              </Checkbox>
            </div>
            <Submit loading={submitting}>登录</Submit>
          </LoginFrom>
        </div>
      </div>
    </div>
  );
};

export default Login;
