// https://umijs.org/config/
import {defineConfig} from 'umi';
import defaultSettings from './defaultSettings';
import proxy from './proxy';

const {REACT_APP_ENV} = process.env;
export default defineConfig({
  hash: true,
  antd: {},
  dva: {
    hmr: true,
  },
  layout: {
    name: 'Ant Design Pro',
    locale: true,
    siderWidth: 208,
  },
  locale: {
    // default zh-CN
    default: 'zh-CN',
    // default true, when it is true, will use `navigator.language` overwrite default
    antd: true,
    baseNavigator: true,
  },
  dynamicImport: {
    loading: '@/components/PageLoading/index',
  },
  targets: {
    ie: 11,
  },
  // umi routes: https://umijs.org/docs/routing
  routes: [
    {
      path: '/user',
      layout: false,
      routes: [
        {
          name: 'login',
          path: '/user/login',
          component: './user/login',
        },
      ],
    },
    {
      name: '分析',
      icon: 'smile',
      path: '/',
      component: './dashboard',
    },
    {
      name: '管理',
      icon: 'smile',
      path: '/manage',
      routes: [
        {
          name: '产品',
          icon: 'smile',
          path: 'manage/product',
          component: './manage/product',
        },
        {
          name: '供应商',
          icon: 'smile',
          path: '/manage/supplier',
          component: './manage/supplier',
        },
        {
          name: '客户',
          icon: 'smile',
          path: '/manage/customer',
          component: './manage/customer',
        },
      ],
    },
    {
      name: '库存',
      icon: 'smile',
      path: 'inventory',
      component: './inventory',
    },
    {
      component: './404',
    },
  ],
  // Theme for antd: https://ant.design/docs/react/customize-theme-cn
  theme: {
    // ...darkTheme,
    'primary-color': defaultSettings.primaryColor,
  },
  ignoreMomentLocale: true,
  proxy: proxy[REACT_APP_ENV || 'dev'],
  manifest: {
    basePath: '/',
  },
});
