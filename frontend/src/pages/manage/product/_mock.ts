// eslint-disable-next-line import/no-extraneous-dependencies
import {Request, Response} from 'express';
import {BasicListItemDataType} from './data.d';

const code = [
  'P-001',
  'P-002',
  'P-003',
  'P-004',
  'P-005',
  'P-006',
  'P-007',
  'P-008',
];
const images = [
  'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png', // Alipay
  'https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png', // Angular
  'https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png', // Ant Design
  'https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png', // Ant Design Pro
  'https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png', // Bootstrap
  'https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png', // React
  'https://gw.alipayobjects.com/zos/rmsportal/ComBAopevLwENQdKWiIn.png', // Vue
  'https://gw.alipayobjects.com/zos/rmsportal/nxkuOJlFJuAUhzlMTCEe.png', // Webpack
];

const name = [
  '童装（上衣）',
  '童装（衬衫）',
  '童装（衬衫）',
  '童装（衬衫）',
  '童装（衬衫）',
  '童装（衬衫）',
  '童装（衬衫）',
  '童装（裤子）',
];

const size = [
  '大',
  '小',
  '中',
  '中',
  '中',
  '中',
  '中',
  '中',
];

function fakeList(count: number): BasicListItemDataType[] {
  const list = [];
  for (let i = 0; i < count; i += 1) {
    list.push({
      id: `fake-list-${i}`,
      size: size[i % 10],
      code: code[i % 8],
      image: images[i % 8],
      createdAt: new Date(new Date().getTime() - 1000 * 60 * 60 * 2 * i).getTime(),
      name: name[i % 5],
      description:
        '这个是一条备注，用来显示这个产品的特色',
    });
  }

  return list;
}

let sourceData: BasicListItemDataType[] = [];

function getFakeList(req: Request, res: Response) {
  const params: any = req.query;

  const count = params.count * 1 || 20;

  const result = fakeList(count);
  sourceData = result;
  return res.json(result);
}

function postFakeList(req: Request, res: Response) {
  const { /* url = '', */ body} = req;
  // const params = getUrlParams(url);
  const {method, id} = body;
  // const count = (params.count * 1) || 20;
  let result = sourceData || [];

  switch (method) {
    case 'delete':
      result = result.filter((item) => item.id !== id);
      break;
    case 'update':
      result.forEach((item, i) => {
        if (item.id === id) {
          result[i] = {...item, ...body};
        }
      });
      break;
    case 'post':
      result.unshift({
        ...body,
        id: `fake-list-${result.length}`,
        createdAt: new Date().getTime(),
      });
      break;
    default:
      break;
  }

  return res.json(result);
}

export default {
  'GET  /api/product/fake_list': getFakeList,
  'POST  /api/product/fake_list': postFakeList,
};
