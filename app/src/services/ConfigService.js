import * as request from '@/utils/request';

const ConfigService = {};

ConfigService.selectSystemInfo = (successHandler, failHandler) => {
  return request.get(
    '/api/configs/systems/info',
    null,
    res => {
      successHandler(res);
    },
    failHandler,
  );
};

ConfigService.selectTestcaseConfigs = (successHandler, failHandler) => {
  return request.get(
    '/api/configs/systems/testcase/configs',
    null,
    res => {
      successHandler(res);
    },
    failHandler,
  );
};

ConfigService.createSetUpInfo = (setupInfo, successHandler, failHandler) => {
  return request.post(
    '/api/configs/systems/setup',
    setupInfo,
    res => {
      successHandler(res);
    },
    failHandler,
  );
};

ConfigService.sendTestMessageToSlack = (slackUrl, successHandler, failHandler) => {
  return request.post(
    '/api/configs/systems/slack',
    { slackUrl },
    res => {
      successHandler(res);
    },
    failHandler,
  );
};

ConfigService.createArithmeticException = (successHandler, failHandler) => {
  return request.get(
    '/api/configs/systems/errors/arithmetic',
    null,
    res => {
      successHandler(res);
    },
    failHandler,
  );
};

ConfigService.createServiceException = (successHandler, failHandler) => {
  return request.get(
    '/api/configs/systems/errors/service',
    null,
    res => {
      successHandler(res);
    },
    failHandler,
  );
};

export default ConfigService;
