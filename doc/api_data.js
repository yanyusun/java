define({ "api": [
  {
    "type": "POST",
    "url": "http://{url}/auth/addCompany",
    "title": "完善公司信息",
    "name": "addCompany",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "companyName",
            "description": "<p>公司名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "credential",
            "description": "<p>加密Key</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "licence",
            "description": "<p>加密Key</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "province",
            "description": "<p>省份Id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "city",
            "description": "<p>城市Id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "area",
            "description": "<p>区域Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>详细地址</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/addCompany"
      }
    ],
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-user",
            "description": "<p>账号加密字符串</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-type",
            "description": "<p>账号类型集</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-role",
            "description": "<p>账号角色权限集</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-certified",
            "description": "<p>账号认证情况集合</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-status",
            "description": "<p>账号状态</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>错误消息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    code:2000,\n    msg:'成功',\n    data:JsonObject\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/addCompany_four",
    "title": "完善公司信息",
    "name": "addCompany",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "companyName",
            "description": "<p>公司名称</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "credential",
            "description": "<p>加密Key</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "licence",
            "description": "<p>加密Key</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>公司类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "userType",
            "description": "<p>用户类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "realName",
            "description": "<p>姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "identity",
            "description": "<p>身份证</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "smsCode",
            "description": "<p>验证码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/addCompany_four"
      }
    ],
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-user",
            "description": "<p>账号加密字符串</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-type",
            "description": "<p>账号类型集</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-role",
            "description": "<p>账号角色权限集</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-certified",
            "description": "<p>账号认证情况集合</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-status",
            "description": "<p>账号状态</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>错误消息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    code:2000,\n    msg:'成功',\n    data:JsonObject\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "GET",
    "url": "http://{url}/auth/captcha",
    "title": "图片验证码",
    "name": "captcha",
    "group": "Auth",
    "description": "<p>This is the Description.</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "key",
            "description": "<p>验证码标签key</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>png图片</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/captcha"
      }
    ]
  },
  {
    "type": "GET",
    "url": "http://{url}/auth/confirm_mail",
    "title": "邮箱激活",
    "name": "confirm_mail",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "confirmKey",
            "description": "<p>加密Key</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/confirm_mail"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>错误消息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    code:2000,\n    msg:'成功',\n    data:JsonObject\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/login",
    "title": "用户登陆",
    "name": "login",
    "group": "Auth",
    "description": "<p>用户登录模块返回信息包含独有信息, 具体参考Login-Success-Response</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "data",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>错误消息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Login-Success-Response:",
          "content": "{\n'x-qs-user':'SDFBSKDFNSDFNSKJFSDFNLSDFNLSDFNK=',\n'x-qs-type':'0,',\n'x-qs-role':'0,',\n'x-qs-certified':'true,',\n'x-qs-status':'0,',\n'x-qs-step':'true'\n}\n参数step说明:false:无效账户,active:激活邮箱,adminCompany:未完善信息,authentication:企业未认证,true:信息完善",
          "type": "json"
        },
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    code:2000,\n    msg:'成功',\n    data:JsonObject\n}",
          "type": "json"
        },
        {
          "title": "Data-Success-Response:",
          "content": "{\n    'x-qs-user':'SDFBSKDFNSDFNSKJFSDFNLSDFNLSDFNK=',\n    'x-qs-type':'0,',\n    'x-qs-role':'0,',\n    'x-qs-certified':'true,',\n    'x-qs-status':'0,'\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/login"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "account",
            "description": "<p>账号&lt;三选一必选&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "mobile",
            "description": "<p>手机号&lt;三选一必选&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "email",
            "description": "<p>邮箱&lt;三选一必选&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pwd",
            "description": "<p>密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "smsCode",
            "description": "<p>手机短信验证码&lt;三选一必选&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "captcha",
            "description": "<p>图片验证码&lt;三选一必选&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "captchaKey",
            "description": "<p>图片验证码Key&lt;三选一必选&gt;</p>"
          }
        ]
      }
    }
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/register",
    "title": "注册",
    "name": "register",
    "group": "Auth",
    "description": "<p>用户注册</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "ResponseHeader",
            "description": ""
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>错误消息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    code:2000,\n    msg:'成功',\n    data:JsonObject\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/register"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "account",
            "description": "<p>账号&lt;三选一必选&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "mobile",
            "description": "<p>手机号&lt;三选一必选&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "email",
            "description": "<p>邮箱&lt;三选一必选&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pwd",
            "description": "<p>密码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "smsCode",
            "description": "<p>手机短信验证码&lt;三选一必选&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "captcha",
            "description": "<p>图片验证码&lt;三选一必选&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "captchaKey",
            "description": "<p>图片验证码Key&lt;三选一必选&gt;</p>"
          }
        ]
      }
    }
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/fixCompanyInfo",
    "title": "完善公司以及管理人员信息",
    "name": "register_admin",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>名称（相当于公司简称）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "introduction",
            "description": "<p>简介</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "province",
            "description": "<p>省份</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "city",
            "description": "<p>城市</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "district",
            "description": "<p>区域</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>空返回值</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/fixCompanyInfo"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/register_admin",
    "title": "完善管理员信息",
    "name": "register_admin",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "userId",
            "description": "<p>用户ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "userType",
            "description": "<p>用户类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "companyId",
            "description": "<p>公司ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "confirmKey",
            "description": "<p>加密Key</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "identity",
            "description": "<p>身份证</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "smsCode",
            "description": "<p>验证码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/register_admin"
      }
    ],
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-user",
            "description": "<p>账号加密字符串</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-type",
            "description": "<p>账号类型集</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-role",
            "description": "<p>账号角色权限集</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-certified",
            "description": "<p>账号认证情况集合</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-status",
            "description": "<p>账号状态</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>错误消息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    code:2000,\n    msg:'成功',\n    data:JsonObject\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/reset",
    "title": "重置密码",
    "name": "reset",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pwd",
            "description": "<p>新密码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/reset"
      }
    ],
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-user",
            "description": "<p>账号加密字符串</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-type",
            "description": "<p>账号类型集</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-role",
            "description": "<p>账号角色权限集</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-certified",
            "description": "<p>账号认证情况集合</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-status",
            "description": "<p>账号状态</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>错误消息</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "data",
            "description": "<p>返回头信息内容</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    code:2000,\n    msg:'成功',\n    data:JsonObject\n}",
          "type": "json"
        },
        {
          "title": "Data-Success-Response:",
          "content": "{\n    'x-qs-user':'SDFBSKDFNSDFNSKJFSDFNLSDFNLSDFNK=',\n    'x-qs-type':'0,',\n    'x-qs-role':'0,',\n    'x-qs-certified':'true,',\n    'x-qs-status':'0,'\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/resetMail",
    "title": "邮箱重置密码",
    "name": "resetMail",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mailCode",
            "description": "<p>邮件验证码</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pwd",
            "description": "<p>密码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/resetMail"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/resetMobile",
    "title": "根据邮箱进行手机验证码重置密码",
    "name": "resetMobile",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "smsCode",
            "description": "<p>手机验证码</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pwd",
            "description": "<p>密码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/resetMobile"
      }
    ]
  },
  {
    "type": "GET",
    "url": "http://{url}/auth/reset_mail",
    "title": "邮件重置密码",
    "name": "reset_mail",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "uid",
            "description": "<p>用户Id</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "confirmKey",
            "description": "<p>验证key</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pwd",
            "description": "<p>新密码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/reset_mail"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>错误消息</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "data",
            "description": "<p>返回头信息内容</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    code:2000,\n    msg:'成功',\n    data:JsonObject\n}",
          "type": "json"
        },
        {
          "title": "Data-Success-Response:",
          "content": "{\n    'x-qs-user':'SDFBSKDFNSDFNSKJFSDFNLSDFNLSDFNK=',\n    'x-qs-type':'0,',\n    'x-qs-role':'0,',\n    'x-qs-certified':'true,',\n    'x-qs-status':'0,'\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/reset_mobile",
    "title": "使用手机短信验证码来重置密码",
    "name": "reset_mobile",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "smsCode",
            "description": "<p>验证码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "pwd",
            "description": "<p>新密码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/reset_mobile"
      }
    ],
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>错误消息</p>"
          },
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "data",
            "description": "<p>返回头信息内容</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    code:2000,\n    msg:'成功',\n    data:JsonObject\n}",
          "type": "json"
        },
        {
          "title": "Data-Success-Response:",
          "content": "{\n    'x-qs-user':'SDFBSKDFNSDFNSKJFSDFNLSDFNLSDFNK=',\n    'x-qs-type':'0,',\n    'x-qs-role':'0,',\n    'x-qs-certified':'true,',\n    'x-qs-status':'0,'\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/sendEmaiCode",
    "title": "发送邮箱邮件验证码",
    "name": "sendEmaiCode",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "mailType",
            "description": "<p>邮箱验证码类型（101重置密码）</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/sendEmaiCode"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/sendSmsByCode",
    "title": "发送手机短信验证吗",
    "name": "sendSmsByCode",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "smsType",
            "description": "<p>验证码类型（101注册验证码。138找回密码）</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/sendSmsByCode"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/sendSmsCodeByEmail",
    "title": "根据邮箱发送手机验证码进行重置密码操作",
    "name": "sendSmsCodeByEmail",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/sendSmsCodeByEmail"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/send_mail",
    "title": "发送邮件",
    "name": "send_mail",
    "group": "Auth",
    "description": "<p>发送至当前用户绑定的邮箱</p>",
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/send_mail"
      }
    ],
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-user",
            "description": "<p>账号加密字符串</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-type",
            "description": "<p>账号类型集</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-role",
            "description": "<p>账号角色权限集</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-certified",
            "description": "<p>账号认证情况集合</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "x-qs-status",
            "description": "<p>账号状态</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>错误消息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    code:2000,\n    msg:'成功',\n    data:JsonObject\n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "GET",
    "url": "http://{url}/auth/sms_code",
    "title": "短信验证码",
    "name": "smsCode",
    "group": "Auth",
    "description": "<p>手机校验短信</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>错误消息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    code:2000,\n    msg:'成功',\n    data:JsonObject\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/sms_code"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/updatePaw",
    "title": "根据原密码进行修改密码",
    "name": "updatePaw",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "oldPaw",
            "description": "<p>旧密码</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "newPaw",
            "description": "<p>新密码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/updatePaw"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/validMailCode",
    "title": "验证邮箱邮件验证码",
    "name": "validMailCode",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mailCode",
            "description": "<p>邮件验证码</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "mailType",
            "description": "<p>验证码类型（101重置密码）</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/validMailCode"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/validSmsCode",
    "title": "验证手机短信验证吗",
    "name": "validSmsCode",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "smsCode",
            "description": "<p>手机验证码</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "smsType",
            "description": "<p>验证码类型（101注册验证码。138找回密码）</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/validSmsCode"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/validSmsCodeByEmail",
    "title": "根据邮箱获取手机号，并手机验证码进行验证",
    "name": "validSmsCodeByEmail",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "smsCode",
            "description": "<p>手机验证码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/validSmsCodeByEmail"
      }
    ]
  },
  {
    "type": "GET",
    "url": "http://{url}/auth/validate_user",
    "title": "验证用户有效性",
    "name": "validateUser",
    "group": "Auth",
    "description": "<p>验证当前账号是否有效</p>",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "userName",
            "description": "<p>账号&lt;三选一必选&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "mobile",
            "description": "<p>手机号三选一必选&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "email",
            "description": "<p>邮箱三选一必选&gt;</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "json",
            "optional": false,
            "field": "data",
            "description": "<p>返回数据(0,用户可用;Object,已存在且读取出数据)</p>"
          },
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>错误码</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "msg",
            "description": "<p>错误消息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    code:2000,\n    msg:'成功',\n    data:JsonObject\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 2000 ok\n{\ncode:2000,\nmsg:'成功',\ndata:0\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/validate_user"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/verifyEmailAndCode",
    "title": "找回密码，邮箱邮件验证码验证",
    "name": "verifyEmailAndCode",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mailCode",
            "description": "<p>邮件验证码</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "code",
            "description": "<p>验证码</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "captchaKey",
            "description": "<p>图片验证码的key</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/verifyEmailAndCode"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/verifyImgCode",
    "title": "验证图片验证码",
    "name": "verifyImgCode",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "code",
            "description": "<p>验证码</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "captchaKey",
            "description": "<p>图片验证码的key</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/verifyImgCode"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/auth/verifyLoginName 验证自定义帐号、邮箱、手机号三者唯一性，",
    "title": "只是验证其中一个唯一性的就传相应的参数即可",
    "name": "verifyLoginName",
    "group": "Auth",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "account",
            "description": "<p>自定义帐号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "email",
            "description": "<p>邮箱</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_auth/src/main/java/com/dqys/auth/controller/AuthController.java",
    "groupTitle": "Auth",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/auth/verifyLoginName 验证自定义帐号、邮箱、手机号三者唯一性，"
      }
    ]
  },
  {
    "type": "post",
    "url": "coordinator/addInitiative",
    "title": "人员主动添加到案组",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userTeammateId",
            "description": "<p>协作器id</p>"
          }
        ]
      }
    },
    "description": "<p>主动加入案组</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/addInitiative"
      }
    ],
    "group": "Coordinator",
    "name": "coordinator_addInitiative",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "Coordinator"
  },
  {
    "type": "post",
    "url": "coordinator/addTeammate",
    "title": "添加邀请人员",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userTeamId",
            "description": "<p>协作器id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "remark",
            "description": "<p>验证消息</p>"
          },
          {
            "group": "Parameter",
            "type": "int[]",
            "optional": false,
            "field": "userIds",
            "description": "<p>被邀请人的id</p>"
          }
        ]
      }
    },
    "description": "<p>添加邀请人员到参与人关联表</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/addTeammate"
      }
    ],
    "group": "Coordinator",
    "name": "coordinator_addTeammate",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "Coordinator"
  },
  {
    "type": "post",
    "url": "coordinator/auditBusiness",
    "title": "平台业务审核",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型(10资产包11借款人)</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>状态（0重新申请1通过2不通过）</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/auditBusiness"
      }
    ],
    "group": "Coordinator",
    "name": "coordinator_auditBusiness",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "Coordinator"
  },
  {
    "type": "post",
    "url": "coordinator/delUser",
    "title": "协作器删除参与人",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "teamUserId",
            "description": "<p>原参与处置的人userId</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userTeamId",
            "description": "<p>团队协作器id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>状态（0同意1拒绝）</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "substitutionUid",
            "description": "<p>替补人userId</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "operUserId",
            "description": "<p>邀请发起人</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/delUser"
      }
    ],
    "group": "Coordinator",
    "name": "coordinator_delUser",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "Coordinator"
  },
  {
    "type": "post",
    "url": "coordinator/getUserDetail",
    "title": "获取员工明信片",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/getUserDetail"
      }
    ],
    "group": "Coordinator",
    "name": "coordinator_getUserDetail",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"detail\": {\n\"userId\": 285,\n\"companyId\": 409,\n\"realName\": \"测试丰\",//真实姓名\n\"companyName\": \"风腾律所\",//公司名称\n\"mobile\": \"18267903327\",//手机号\n\"avg\": null,//头像地址\n\"roleType\": 1,//角色类型（1-管理员;2-管理者;3-普通员工）\n\"userType\": 31,//用户类型（0普通用户1平台管理员2委托31催收32司法33中介）\n\"occupation\": null//职位\n}\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "Coordinator"
  },
  {
    "type": "post",
    "url": "coordinator/history",
    "title": "协作器历史参与人",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userTeamId",
            "description": "<p>公司ID</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/history"
      }
    ],
    "group": "Coordinator",
    "name": "coordinator_history",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"result\": \"yes\",\n\"teams\": [\n{\n\"userId\": 296,\n\"roleType\": 1,\n\"realName\": \"admin\",\n\"teamName\": \"清搜团队\",\n\"finishTask\": 0,\n\"totalTask\": 0,\n\"ongoingTask\": 0,\n\"leaveWordTime\": \"2016-11-18\",\n\"status\": 99,\n\"joinType\": 0,\n\"avg\": \"1_296_1479464843315.jpg\"\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "Coordinator"
  },
  {
    "type": "post",
    "url": "coordinator/isAccept",
    "title": "被邀请人员同意或是拒绝请求",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "teammateId",
            "description": "<p>参与人关联表id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "operUserId",
            "description": "<p>邀请发起人</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>状态（1同意2拒绝）</p>"
          }
        ]
      }
    },
    "description": "<p>被邀请人员确认和拒绝</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/isAccept"
      }
    ],
    "group": "Coordinator",
    "name": "coordinator_isAccept",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "Coordinator"
  },
  {
    "type": "post",
    "url": "coordinator/isPause",
    "title": "借款人或资产包暂停无效操作",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型(10资产包11借款人16资产源)</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>状态（0开启1暂停2无效）</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/isPause"
      }
    ],
    "group": "Coordinator",
    "name": "coordinator_isPause",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "Coordinator"
  },
  {
    "type": "post",
    "url": "coordinator/list",
    "title": "协作器借款人或是资产包的参与者列表",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "companyId",
            "description": "<p>公司ID</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "type",
            "description": "<p>请求类型（1借款人2资产包3抵押物4资产源5案件）</p>"
          }
        ]
      }
    },
    "description": "<p>查询借款人或是资产包信息</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/list"
      }
    ],
    "group": "Coordinator",
    "name": "coordinator_list",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"result\": \"yes\",//反馈结果状态（成功yes）\n\"loan\": 2221,//总贷款\n\"dayCount\": 22,//逾期天数\n\"teams\": [\n{\n\"userId\": 11,//用户id\n\"roleType\": 0,//角色类型（0管理者1所属人2参与人）\n\"realName\": \"WZHPAN\",//真实姓名\n\"teamName\": \"研发部门\",//团队名称\n\"finishTask\": 0,//完成任务数\n\"totalTask\": 3,//总任务数\n\"ongoingTask\": 0,//当前进行任务数\n\"leaveWordTime\": \"\"//最后留言时间\n}\n],\n\"companys\": [\n{\n\"company_name\": \"test\", //公司名称\n\"id\": 3, //公司id\n\"userType\": 1 //用户类别（1平台 2委托 31催收 32律所 33中介）\n}\n],\n\"name\": null, //名称\n\"appraisal\": 31, //抵押物总评估\n\"accrual\": 313,//总利息\n\"userTeamId\": 352,//协作器id\n\"people\": [\n{\n\"type\": 0,//角色类型(0管理者，1所属人 ，2参与人)\n\"peopleNum\": 1 //对应的人数\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "Coordinator"
  },
  {
    "type": "post",
    "url": "coordinator/publish",
    "title": "录入信息的发布",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型(10资产包11借款人)</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>状态（0发布）</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/publish"
      }
    ],
    "group": "Coordinator",
    "name": "coordinator_publish",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "Coordinator"
  },
  {
    "type": "post",
    "url": "coordinator/setDeadline",
    "title": "设置委托期限",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "dateTime",
            "description": "<p>委托期限（格式：yyyy-MM-dd）</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/setDeadline"
      }
    ],
    "group": "Coordinator",
    "name": "coordinator_setDeadline",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "Coordinator"
  },
  {
    "type": "post",
    "url": "coordinator/userList",
    "title": "选择人员",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "realName",
            "description": "<p>用户名称或真实姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userTeamId",
            "description": "<p>协作器id</p>"
          }
        ]
      }
    },
    "description": "<p>添加参与人的时候选择人员（公司所有员工）</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/userList"
      }
    ],
    "group": "Coordinator",
    "name": "coordinator_userList",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"users\": [\n{\n\"realName\": \"张三\",//姓名\n\"companyId\": 3,//公司id\n\"companyName\": \"test\",//公司名称\n\"userName\": \"zs\",//用户名称\n\"roleType\": 1,//角色（1管理员，2管理者，3普通）\n\"userType\": 31,//用户类型（31催收32律所33中介）\n\"userId\": 12 //用户id\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "Coordinator"
  },
  {
    "type": "post",
    "url": "back/addCoupleBack",
    "title": "添加反馈信息",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/back/addCoupleBack"
      }
    ],
    "group": "CoupleBack",
    "name": "back_addCoupleBack",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoupleBackController.java",
    "groupTitle": "",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "content",
            "description": "<p>内容</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "type",
            "description": "<p>类型（0默认1资讯2建议3其他）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "other",
            "description": "<p>其他信息</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "back/addMessage",
    "title": "添加回复信息",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/back/addMessage"
      }
    ],
    "group": "CoupleBack",
    "name": "back_addMessage",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoupleBackController.java",
    "groupTitle": "",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "tcbId",
            "description": "<p>反馈信息id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "contentMessage",
            "description": "<p>内容</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "leaveUserId",
            "description": "<p>被回复用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "back/delCoupleBack",
    "title": "删除反馈信息",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "id",
            "description": "<p>反馈信息id</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/back/delCoupleBack"
      }
    ],
    "group": "CoupleBack",
    "name": "back_delCoupleBack",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoupleBackController.java",
    "groupTitle": ""
  },
  {
    "type": "post",
    "url": "back/delMessage",
    "title": "删除回复信息",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "id",
            "description": "<p>回复信息id</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/back/delMessage"
      }
    ],
    "group": "CoupleBack",
    "name": "back_delMessage",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoupleBackController.java",
    "groupTitle": ""
  },
  {
    "type": "post",
    "url": "back/listBack",
    "title": "反馈信息列表",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "type",
            "description": "<p>类型（0默认1资讯2建议3其他）</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/back/listBack"
      }
    ],
    "group": "CoupleBack",
    "name": "back_listBack",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"result\": \"yes\",\n\"list\": [\n{\n\"id\": 1,//反馈信息id\n\"userName\": \"麦克风\",//用户名\n\"realName\": \"麦克风\",//真实姓名\n\"createUser\": 409,//用户id\n\"createTime\": \"2016-11-16 16:10:10\",//反馈时间\n\"content\": \"1\",//内容\n\"type\": 1 //类型（0默认1资讯2建议3其他）\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoupleBackController.java",
    "groupTitle": ""
  },
  {
    "type": "post",
    "url": "back/listMessage",
    "title": "反馈信息回复列表",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "tcbId",
            "description": "<p>反馈信息id</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/back/listMessage"
      }
    ],
    "group": "CoupleBack",
    "name": "back_listMessage",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"result\": \"yes\",\n\"list\": [\n{\n\"userId\": 411,//回复用户的用户id\n\"tcbId\": 1,//反馈信息的id\n\"contentMessage\": \"就是这么嗨\",//回复内容\n\"leaveUserId\": 409,//被回复用户的用户id（这个被回复用户只有在messType为1才有数据）\n\"messType\": 1,//信息类型（0主动留言1回复留言）\n\"createTime\": \"2016-11-16 18:52:05\",//回复信息时间\n\"leaveUserName\": \"麦克风\",//被回复用户的用户名\n\"userName\": null,//回复用户的用户名\n\"leaveRealName\": \"麦克风\",//被回复用户的真实姓名\n\"realName\": \"龚子鸣\"//回复用户的真实姓名\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoupleBackController.java",
    "groupTitle": ""
  },
  {
    "type": "post",
    "url": "index/general",
    "title": "任务统计",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/index/general"
      }
    ],
    "group": "INDEX",
    "name": "index_general",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"await\": 0,//待完成任务-------------\n\"finish\": 0,//已完成------------\n\"unfinished\": 2,//正在进行中------------\n\"join\": 1, //参与的任务\n\"detail\": {\n\"roleName\": \"管理员\",//角色类型\n\"realName\": \"测试丰\",//姓名\n\"adminName\": \"测试丰\",//管理员姓名\n\"finishRate\": \"0%\",//业绩比率\n\"lastTime\": null,//上一次登入时间\n\"province\": \"北京市\",//省份\n\"city\": \"市辖区\",//城市\n\"area\": \"东城区\",//区县\n\"address\": null //地址\n\"avg\": null //头像\n},\n\"notActivated\": 0,//未激活的员工人数\n\"enterToday\": 3,//当天登入的员工人数\n\"totalPeople\": 2 //公司总人数\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/IndexController.java",
    "groupTitle": "INDEX"
  },
  {
    "type": "post",
    "url": "index/getObjectNo",
    "title": "获取各种编号",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/index/getObjectNo"
      }
    ],
    "group": "INDEX",
    "name": "index_getObjectNo",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "code",
            "description": "<p>编号规则（QS：资产包；LC：案件；IO：借据；BO：借款人；CO：抵押物；ZC：资产源；YZ：业主 ；CZ：个人债权；QZ：企业债权；YQ：逾期贷款）</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/IndexController.java",
    "groupTitle": "INDEX"
  },
  {
    "type": "post",
    "url": "message/del",
    "title": "批量删除",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int[]",
            "optional": false,
            "field": "ids",
            "description": "<p>消息id</p>"
          }
        ]
      }
    },
    "description": "<p>批量删除</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/message/del"
      }
    ],
    "group": "Message",
    "name": "message_del",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/MessageController.java",
    "groupTitle": "Message"
  },
  {
    "type": "post",
    "url": "message/getCount",
    "title": "消息数量",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": true,
            "field": "type",
            "description": "<p>消息类型(0, &quot;任务消息&quot;),(1, &quot;产品消息&quot;), (2, &quot;安全消息&quot;),(3, &quot;服务消息&quot;),</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": true,
            "field": "status",
            "description": "<p>消息状态（0未读1已读）</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/message/getCount"
      }
    ],
    "group": "Message",
    "name": "message_getCount",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/MessageController.java",
    "groupTitle": "Message"
  },
  {
    "type": "post",
    "url": "message/pageList",
    "title": "消息列表",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "page",
            "description": "<p>分页数</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "pageCount",
            "description": "<p>显示数目</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "type",
            "description": "<p>类型 (0, &quot;任务消息&quot;),(1, &quot;产品消息&quot;), (2, &quot;安全消息&quot;),(3, &quot;服务消息&quot;),</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>状态（0未读，1已读）</p>"
          }
        ]
      }
    },
    "description": "<p>查询消息列表信息</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/message/pageList"
      }
    ],
    "group": "Message",
    "name": "message_pageList",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"totalMes\": 28,//全部未读消息数\n\"productMes\": 0,//产品未读消息数\n\"safetyMes\": 0,//安全未读消息数\"\n\"taskMes\": 15,//任务未读消息数\"\n\"list\": [\n{\n\"id\": 64,\n\"title\": \"借款人null公司内部邀请\",//标题\n\"contant\": \"协作器邀请验证消息\",//内容\n\"sendTime\": \"2016-09-23\",//发送时间\n\"typeName\": \"任务消息\",// 消息名称\n\"status\": 1,//状态（0未读1已读）\n\"label\": \"任务消息\",//标签\n\"businessType\": 0,// 业务类型\n\"operUrl\": \"{\\\"distribution\\\":\\\"\\\",\\\"accept\\\":\\\"/coordinator/isAccept?status=1&teammateId=23\\\",\\\"acceptRequestType\\\":\\\"get\\\",\\\"reject\\\":\\\"/coordinator/isAccept?status=2&teammateId=23\\\",\\\"rejectRequestType\\\":\\\"get\\\"}\"//json格式操作地址\n}\n],\n\"listCount\": 50,//条件查询消息数量（分页使用）\n\"serveMes\": 13 //服务未读消息数\"\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/MessageController.java",
    "groupTitle": "Message"
  },
  {
    "type": "post",
    "url": "message/read",
    "title": "标记为已读(单个或批量)",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int[]",
            "optional": false,
            "field": "id",
            "description": "<p>消息id</p>"
          }
        ]
      }
    },
    "description": "<p>标记为已读(单个或批量)</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/message/read"
      }
    ],
    "group": "Message",
    "name": "message_read",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/MessageController.java",
    "groupTitle": "Message"
  },
  {
    "type": "post",
    "url": "message/setOper",
    "title": "修改操作状态",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "id",
            "description": "<p>消息id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>操作状态（0默认未操作1同意2拒绝）</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/message/setOper"
      }
    ],
    "group": "Message",
    "name": "message_setOper",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/MessageController.java",
    "groupTitle": "Message"
  },
  {
    "type": "GET",
    "url": "http://{url}/operType/listbuttonShower",
    "title": "按钮显示接口",
    "name": "listbuttonShower",
    "group": "OperType",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "navId",
            "description": "<p>对象类型</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"hasRightButton\": true,\n\"hasUserTeamButton\": false,\n\"hasUserTeamButtonApply\": false,\n\"hasUserTeamButtonAdd\": false,\n\"hasCompanyTeamButton\": true,\n\"hasCompanyTeamButtonApply\": false,\n\"hasCompanyTeamButtonAdd\": true,\n\"rightButtonList\": [\n[\n\"1\",\n\"操作记录\"\n]\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/OperTypeController.java",
    "groupTitle": "OperType",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/operType/listbuttonShower"
      }
    ]
  },
  {
    "type": "post",
    "url": "operType/list",
    "title": "操作接口",
    "name": "operType_list",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/operType/list"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "navId",
            "description": "<p>导航id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "position",
            "description": "<p>位置</p>"
          }
        ]
      }
    },
    "group": "OperType",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": [\n{\n\"id\": 119,\n\"operType\": 111,\n\"operName\": \"修改/编辑\"\n},\n{\n\"id\": 120,\n\"operType\": 112,\n\"operName\": \"添加关注\"\n},\n{\n\"id\": 121,\n\"operType\": 113,\n\"operName\": \"评优/内部评级\"\n},\n{\n\"id\": 122,\n\"operType\": 114,\n\"operName\": \"申请律师函\"\n},\n{\n\"id\": 123,\n\"operType\": 115,\n\"operName\": \"申请延期\"\n},\n{\n\"id\": 124,\n\"operType\": 116,\n\"operName\": \"设置为无效\"\n},\n{\n\"id\": 125,\n\"operType\": 117,\n\"operName\": \"录跟进\"\n},\n{\n\"id\": 126,\n\"operType\": 118,\n\"operName\": \"增加注释\"\n},\n{\n\"id\": 127,\n\"operType\": 119,\n\"operName\": \"修改日志\"\n},\n{\n\"id\": 128,\n\"operType\": 1110,\n\"operName\": \"操作日志\"\n},\n{\n\"id\": 129,\n\"operType\": 1111,\n\"operName\": \"添加一条还款\"\n},\n{\n\"id\": 130,\n\"operType\": 1112,\n\"operName\": \"分配借款人\"\n}\n]\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/OperTypeController.java",
    "groupTitle": "OperType"
  },
  {
    "type": "GET",
    "url": "http://{url}/operType/repayButtonShower",
    "title": "是否显示添加还款记录按钮",
    "name": "repayButtonShower",
    "group": "OperType",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "lenderId",
            "description": "<p>对象id</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": true\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/OperTypeController.java",
    "groupTitle": "OperType",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/operType/repayButtonShower"
      }
    ]
  },
  {
    "type": "post",
    "url": "repay/auditPostpone",
    "title": "延期申请审核操作",
    "name": "repay_auditPostpone",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/repay/auditPostpone"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "applyId",
            "description": "<p>延期记录id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>审核状态（1通过2拒绝）</p>"
          }
        ]
      }
    },
    "group": "Repay",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/RepayController.java",
    "groupTitle": "Repay"
  },
  {
    "name": "repay_caseRepayMoney",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/repay/caseRepayMoney"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "caseId",
            "description": "<p>案件id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "file",
            "description": "<p>单据图片</p>"
          }
        ]
      }
    },
    "group": "Repay",
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/RepayController.java",
    "groupTitle": "Repay"
  },
  {
    "type": "post",
    "url": "repay/getIouAndPawnByLender",
    "title": "根据借款人id获取底下的借据和抵押物",
    "name": "repay_getIouAndPawnByLender",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/repay/getIouAndPawnByLender"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "lenderId",
            "description": "<p>借款人id</p>"
          }
        ]
      }
    },
    "group": "Repay",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"pawns\": [\n{\n\"number\": \"CO16090004\",//编号\n\"id\": 843 //抵押物id\n}\n],\n\"ious\": [\n{\n\"number\": \"IO16090002\",//编号\n\"id\": 540 //借据id\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/RepayController.java",
    "groupTitle": "Repay"
  },
  {
    "name": "repay_lenderRepayMoney",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/repay/lenderRepayMoney"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "lenderId",
            "description": "<p>借款人id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "file",
            "description": "<p>单据图片</p>"
          }
        ]
      }
    },
    "group": "Repay",
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/RepayController.java",
    "groupTitle": "Repay"
  },
  {
    "name": "repay_pawnOrIouRepayMoney",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/repay/pawnOrIouRepayMoney"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型(1借据2抵押物)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "file",
            "description": "<p>单据图片</p>"
          }
        ]
      }
    },
    "group": "Repay",
    "type": "",
    "url": "",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/RepayController.java",
    "groupTitle": "Repay"
  },
  {
    "type": "post",
    "url": "repay/postpone",
    "title": "延期申请操作",
    "name": "repay_postpone",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/repay/postpone"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型（11借款人10资产包）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "postponeTime",
            "description": "<p>延期时间（格式：yyyy-MM-dd）</p>"
          }
        ]
      }
    },
    "group": "Repay",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/RepayController.java",
    "groupTitle": "Repay"
  },
  {
    "type": "post",
    "url": "repay/repayList",
    "title": "获取还款记录列表",
    "name": "repay_repayList",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/repay/repayList"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "repayType",
            "description": "<p>还款类型(0还利息1还本金2还利息加本金)</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "repayFidType",
            "description": "<p>对象类型（1借据2抵押物）</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "lenderId",
            "description": "<p>借款人id</p>"
          }
        ]
      }
    },
    "group": "Repay",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"result\": \"yes\",\n\"repays\": [\n{\n\"id\": 59,\n\"damageDate\": \"2016-09-22\",//还款时间,\n\"repayType\": 2,//还款类型(0还利息1还本金2还利息加本金)\n\"operUserId\": 263,//操作员id\n\"repayM\": 1123,// 还款总金额\n\"repayFid\": 528,//还款主体id\n\"repayFidName\": null,//还款主体名称\n\"repayWay\": 2,//还款方式\n\"repayBills\": null,//还款单据图片\n\"remark\": \"\",//备注\n\"version\": 0,//\n\"createAt\": \"2016-09-22\",//\n\"updateAt\": \"2016-09-22\",//\n\"stateflag\": 0,//\n\"repayFidType\": 1,//还款主体类型（借据或抵押物）\n\"status\": 1,//状态（0还清，1还部分，2还款无效）\n\"lenderId\": 346,//借款人id\n\"lenderName\": \"1\",//借款人姓名\n\"operUserName\": \"催收测试\" //操作员姓名\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/RepayController.java",
    "groupTitle": "Repay"
  },
  {
    "type": "post",
    "url": "repay/repayMoney",
    "title": "还款操作",
    "name": "repay_repayMoney",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/repay/repayMoney"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型（1借据2抵押物3不限对象（objectId为借款人id））</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "repayType",
            "description": "<p>还款类型（0还利息1还本金2还利息加本金）</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "repayWay",
            "description": "<p>还款方式 （0直接还款1转贷还款2变卖还款3其他方式）</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "money",
            "description": "<p>还款金额(单位：元)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "file",
            "optional": false,
            "field": "file",
            "description": "<p>文件</p>"
          }
        ]
      }
    },
    "group": "Repay",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/RepayController.java",
    "groupTitle": "Repay"
  },
  {
    "type": "post",
    "url": "repay/reversal",
    "title": "还款冲正操作",
    "name": "repay_reversal",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/repay/reversal"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "repayId",
            "description": "<p>还款记录id</p>"
          }
        ]
      }
    },
    "group": "Repay",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/RepayController.java",
    "groupTitle": "Repay"
  },
  {
    "type": "post",
    "url": "repay/updateRepayMoney",
    "title": "修改还款操作",
    "name": "repay_updateRepayMoney",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/repay/updateRepayMoney"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "repayId",
            "description": "<p>还款记录id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型（1借据2抵押物3不限对象）</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "repayType",
            "description": "<p>还款类型（0还利息1还本金2还利息加本金）</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "repayWay",
            "description": "<p>还款方式 （0直接还款1转贷还款2变卖还款3其他方式）</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "money",
            "description": "<p>还款金额(单位：元)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "file",
            "optional": false,
            "field": "file",
            "description": "<p>文件</p>"
          }
        ]
      }
    },
    "group": "Repay",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/RepayController.java",
    "groupTitle": "Repay"
  },
  {
    "type": "POST",
    "url": "http://{url}/api/user/add",
    "title": "增加用户",
    "name": "add",
    "group": "User",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>添加成功后的ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/add"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>主键</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "avg",
            "description": "<p>头像地址</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "userName",
            "description": "<p>昵称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "sex",
            "description": "<p>性别</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "roleId",
            "description": "<p>角色ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "account",
            "description": "<p>账号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "wechat",
            "description": "<p>微信号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "qq",
            "description": "<p>QQ账号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "occupation",
            "description": "<p>职位</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "occupationTel",
            "description": "<p>座机</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "duty",
            "description": "<p>职责</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "dutyMark",
            "description": "<p>职责介绍</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "apartmentId",
            "description": "<p>部门ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "areaId",
            "description": "<p>职责区域</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "teamId",
            "description": "<p>团队</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "userType",
            "description": "<p>用户类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "companyId",
            "description": "<p>公司Id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "entryTime",
            "description": "<p>入职时间(yyyy-MM-dd)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "yearsLimit",
            "description": "<p>从业年限</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "workStatus",
            "description": "<p>工作状态（0默认在职，1离职，2请假）</p> <p/> Created by Yvan on 16/6/23."
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "api/user/activateReminder",
    "title": "邮箱激活短信提醒，只会发送到未激活的用户手机号",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int[]",
            "optional": false,
            "field": "userIds[i]",
            "description": "<p>用户集合</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/api/user/activateReminder"
      }
    ],
    "group": "User",
    "name": "api_user_activateReminder",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/UserController.java",
    "groupTitle": "User"
  },
  {
    "type": "post",
    "url": "api/user/updateAccountUse",
    "title": "设置帐号使用状态",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int[]",
            "optional": false,
            "field": "userIds[i]",
            "description": "<p>用户集合</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "useStatus",
            "description": "<p>状态（0默认正常，定义&gt;0都无法登入，1帐号停用，2帐号禁止登入）</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/api/user/updateAccountUse"
      }
    ],
    "group": "User",
    "name": "api_user_updateAccountUse",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/UserController.java",
    "groupTitle": "User"
  },
  {
    "type": "get",
    "url": "http://{url}/api/user/delete",
    "title": "删除用户信息",
    "name": "delete",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>用户ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/delete"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "UserDTO:",
          "content": "{\n    \n}",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "POST",
    "url": "http://{url}/api/user/get",
    "title": "查看用户信息",
    "name": "get",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>用户ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "UserInsertDTO",
            "optional": false,
            "field": "data",
            "description": "<p>用户信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "UserInsert:",
          "content": "{\n\"id\": 30,\n\"avg\": null,\n\"userName\": \"name1\",\n\"realName\": \"real11111\",\n\"sex\": 0,\n\"roleId\": 13,\n\"account\": \"asd\",\n\"mobile\": \"13302021212\",\n\"wechat\": \"wechar\",\n\"email\": \"email\",\n\"qq\": null,\n\"occupation\": \"职位\",\n\"occupationTel\": null,\n\"apartmentId\": 1,\n\"duty\": \"经理助理\",\n\"dutyMark\": null,\n\"areaId\": 1128,\n\"teamId\": null,\n\"remark\": null,\n\"userType\": 1,\n\"companyId\": 120\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/user/UserInsertDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/get"
      }
    ]
  },
  {
    "type": "GET",
    "url": "http://{url}/api/user/getInit",
    "title": "获取初始化列表",
    "name": "getInit",
    "group": "User",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "CompanyDTO",
            "optional": false,
            "field": "companyInfo",
            "description": "<p>公司信息</p>"
          },
          {
            "group": "Success 200",
            "type": "SelectonDTO",
            "optional": false,
            "field": "userStatus",
            "description": "<p>用户状态集</p>"
          },
          {
            "group": "Success 200",
            "type": "Property",
            "optional": false,
            "field": "userType",
            "description": "<p>用户账号类型</p>"
          },
          {
            "group": "Success 200",
            "type": "Property",
            "optional": false,
            "field": "roleType",
            "description": "<p>用户角色类型</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CompanyDTO-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    id:1,\n    name:'name',\n    province:'浙江省',\n    city:'杭州市',\n    district:'江干区'\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/CompanyDTO.java",
          "type": "json"
        },
        {
          "title": "SelectDTO-Success-Response:",
          "content": "{\n    key:key,\n    value:\"value\"\n}",
          "type": "json"
        },
        {
          "title": "Property:",
          "content": "{\n    id:1,\n    createAt:\"2011-11-11 11:11:11\",\n    state:0,\n    name:\"name\",\n    value:1\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/getInit"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/api/user/leaveWord",
    "title": "用户留言信息",
    "name": "leaveWord",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "userId",
            "description": "<p>被留言用户</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "content",
            "description": "<p>留言内容</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/leaveWord"
      }
    ]
  },
  {
    "type": "GET",
    "url": "http://{url}/api/user/list",
    "title": "用户列表",
    "name": "list",
    "group": "User",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "UserList",
            "optional": false,
            "field": "data",
            "description": ""
          }
        ]
      },
      "examples": [
        {
          "title": "UserList:",
          "content": "{\nid:1,\nstatus:1,\navg:\"http://114.215.239.181:9988/html/jiagoutu.png\",\nsex:1,\nuserName:\"userName\",\nrealName:\"realName\",\naccount:\"\",\nmobile:\"\",\nemail:\"\",\narea:\"\",\ncompany:\"\",\ntaskNum:0\n}\ngit 地址 : http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/user/UserListDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/list"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "province",
            "description": "<p>省份</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "city",
            "description": "<p>城市</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "district",
            "description": "<p>区域</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "accountType",
            "description": "<p>账号类型&lt;平台方|委托方|处置方|个体用户|c用户&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "role",
            "description": "<p>角色&lt;管理员|负责人|参与人&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "type",
            "description": "<p>类型&lt;机构|个人&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "status",
            "description": "<p>状态&lt;启用|禁用|未激活&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "statuss",
            "description": "<p>状态复选(集合)&lt;启用|禁用|未激活&gt;</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "module",
            "description": "<p>模块（null组织架构，1用户管理）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "name",
            "description": "<p>模糊查询</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "page",
            "description": "<p>页码</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "pageCount",
            "description": "<p>显示条数</p> <p/> Created by Yvan on 16/6/22. 用户模块条件查询"
          }
        ]
      }
    }
  },
  {
    "type": "GET",
    "url": "http://{url}/api/user/listData",
    "title": "二级导航统计",
    "name": "listData",
    "group": "User",
    "description": "<p>二级导航数据统计</p>",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\nplateform:1,\nplateformCompany:1,\nentrustTotal:1,\nentrustAgency:1,\nentrustSingle:1,\ndisposeTotal:1,\nurge:1,\njudicial:1,\ndispose:1,\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/listData"
      }
    ]
  },
  {
    "type": "GET",
    "url": "http://{url}/api/user/listUser",
    "title": "获取公司",
    "name": "listUser",
    "group": "User",
    "description": "<p>暂未补充</p>",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "UserDTO",
            "optional": false,
            "field": "data",
            "description": "<p>用户信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "UserDTO:",
          "content": "{\n    \n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/listUser"
      }
    ]
  },
  {
    "type": "GET",
    "url": "http://{url}/api/user/sendMsg",
    "title": "消息提醒(暂时废除)",
    "name": "sendMsg",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "ids",
            "description": "<p>操作用户ID集合,&quot;,&quot;分隔</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/sendMsg"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/api/user/setPwd",
    "title": "重置密码",
    "name": "setPwd",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>被操作用户</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pwd",
            "description": "<p>新密码</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/setPwd"
      }
    ]
  },
  {
    "type": "GET",
    "url": "http://{url}/api/user/setPwdBatch",
    "title": "批量重置密码",
    "name": "setPwdBatch",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "ids",
            "description": "<p>操作用户ID集合,&quot;,&quot;分隔</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/setPwdBatch"
      }
    ]
  },
  {
    "type": "GET",
    "url": "http://{url}/api/user/statusBatch",
    "title": "批量设置状态",
    "name": "statusBatch",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "ids",
            "description": "<p>操作成员ID的集合,&quot;,&quot;分隔</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "status",
            "description": "<p>状态</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/statusBatch"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/api/user/update",
    "title": "修改用户信息",
    "name": "update",
    "group": "User",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>添加后的Id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/update"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>主键</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "avg",
            "description": "<p>头像地址</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "userName",
            "description": "<p>昵称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "realName",
            "description": "<p>真实姓名</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "sex",
            "description": "<p>性别</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "roleId",
            "description": "<p>角色ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "account",
            "description": "<p>账号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "wechat",
            "description": "<p>微信号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "qq",
            "description": "<p>QQ账号</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "occupation",
            "description": "<p>职位</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "occupationTel",
            "description": "<p>座机</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "duty",
            "description": "<p>职责</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "dutyMark",
            "description": "<p>职责介绍</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "apartmentId",
            "description": "<p>部门ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "areaId",
            "description": "<p>职责区域</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "teamId",
            "description": "<p>团队</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "userType",
            "description": "<p>用户类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "companyId",
            "description": "<p>公司Id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "entryTime",
            "description": "<p>入职时间(yyyy-MM-dd)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "yearsLimit",
            "description": "<p>从业年限</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "workStatus",
            "description": "<p>工作状态（0默认在职，1离职，2请假）</p> <p/> Created by Yvan on 16/6/23."
          }
        ]
      }
    }
  },
  {
    "type": "GET",
    "url": "http://{url}/api/user/userExcel",
    "title": "成员信息excel导入",
    "name": "userExcel",
    "group": "User",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "file",
            "description": "<p>上传的excel文件</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/UserApidoc.java",
    "groupTitle": "User",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/api/user/userExcel"
      }
    ]
  },
  {
    "type": "post",
    "url": "zcy/addEstates",
    "title": "添加资产信息",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "ZcyEstatesAddress",
            "optional": false,
            "field": "zcyEstatesAddressList",
            "description": "<p>资产信息房产地址集合</p>"
          },
          {
            "group": "Parameter",
            "type": "ZcyEstatesFacility",
            "optional": false,
            "field": "zcyEstatesFacilities",
            "description": "<p>标签集合</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "annotation",
            "description": "<p>注释</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "replenish",
            "description": "<p>补充</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseS",
            "description": "<p>户型（室）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseT",
            "description": "<p>户型（厅）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseC",
            "description": "<p>户型（厨）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseW",
            "description": "<p>户型（卫）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseRemark",
            "description": "<p>户型备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "deck",
            "description": "<p>层面</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "quota",
            "description": "<p>限购',</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "acreage",
            "description": "<p>面积</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "sellingPrice",
            "description": "<p>平台售价(单位：万)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "decade",
            "description": "<p>年代</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "property",
            "description": "<p>产权</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "orientation",
            "description": "<p>朝向</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "guidePrice",
            "description": "<p>过户指导价</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "guideRemark",
            "description": "<p>过户指导价备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "entrustType",
            "description": "<p>委托类型</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "decoratePrice",
            "description": "<p>装修费用</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "tenementPrice",
            "description": "<p>物业费</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "facility",
            "description": "<p>嫌恶设施</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "internalNumber",
            "description": "<p>内部编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "title",
            "description": "<p>标题</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "decorateType",
            "description": "<p>装修类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "decorateCase",
            "description": "<p>装修情况</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "decorateTime",
            "description": "<p>装修时间(格式：yyyy-MM-dd)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "buildType",
            "description": "<p>建筑类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseUse",
            "description": "<p>房屋用途</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseBelong",
            "description": "<p>房屋权属</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "heatingWay",
            "description": "<p>供暖方式</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "metro",
            "description": "<p>距地铁</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "schoolHouse",
            "description": "<p>学区房</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "estateType",
            "description": "<p>资产类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "estateClassify",
            "description": "<p>资产归类</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "estatesId",
            "description": "<p>资产信息id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "province",
            "description": "<p>省</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "city",
            "description": "<p>市</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "area",
            "description": "<p>区</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "road",
            "description": "<p>路</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "plotName",
            "description": "<p>小区名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "buildingNo",
            "description": "<p>楼栋</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "element",
            "description": "<p>单元</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "floor",
            "description": "<p>实际楼层</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "floorTotal",
            "description": "<p>共楼层数</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "doorplate",
            "description": "<p>门牌</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "imgUrl",
            "description": "<p>图片地址</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>类型（0配套设施1房源特色2房源缺点）</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "code",
            "description": "<p>编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>名称</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/zcy/addEstates"
      }
    ],
    "group": "ZCY",
    "name": "zcy_addEstates",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/ZcyController.java",
    "groupTitle": "ZCY"
  },
  {
    "type": "post",
    "url": "zcy/addExpress",
    "title": "添加速卖信息",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/zcy/addExpress"
      }
    ],
    "group": "ZCY",
    "name": "zcy_addExpress",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/ZcyController.java",
    "groupTitle": "ZCY",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "protocolNo",
            "description": "<p>协议编号</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "estatesId",
            "description": "<p>资产信息id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "expressPrice",
            "description": "<p>速卖价格(单位：万)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "entrustAbortTime",
            "description": "<p>速卖委托截止时间(格式：yyyy-MM-dd)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "entrustProtocolTime",
            "description": "<p>速卖委托协议时间(格式：yyyy-MM-dd)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "entrustDeposit",
            "description": "<p>委托保证金</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "expressPeople",
            "description": "<p>速 卖 人</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "team",
            "description": "<p>所属团队</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "expressFollow",
            "description": "<p>速卖跟进</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "imgUrl",
            "description": "<p>协议扫描件</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "zcy/addKey",
    "title": "添加钥匙信息",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/zcy/addKey"
      }
    ],
    "group": "ZCY",
    "name": "zcy_addKey",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/ZcyController.java",
    "groupTitle": "ZCY",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "estatesId",
            "description": "<p>资产信息id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "protocolNo",
            "description": "<p>协议编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "entrustProtocolTime",
            "description": "<p>委托协议时间(格式：yyyy-MM-dd)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "entrustAbortTime",
            "description": "<p>委托截止时间(格式：yyyy-MM-dd)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "keyNum",
            "description": "<p>钥匙套数</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "keyPlace",
            "description": "<p>钥匙存放位置</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "keyFollow",
            "description": "<p>钥匙跟进</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "zcy/addMaintain",
    "title": "添加维护信息",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "ZcyMaintainOther",
            "optional": false,
            "field": "zcyMaintainOthers",
            "description": "<p>维护信息标签集合</p>"
          },
          {
            "group": "Parameter",
            "type": "ZcyMaintainTax",
            "optional": false,
            "field": "zcyMaintainTaxes",
            "description": "<p>维护信息税收集合</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "estatesId",
            "description": "<p>资产信息id</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "originalPrice",
            "description": "<p>原购房价格（单位：万）</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "marketPrice",
            "description": "<p>市场评估价（单位：万）</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "agencyPrice",
            "description": "<p>代理价格（单位：万）</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "profitPrice",
            "description": "<p>建议设置利润价（单位：万）</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "remodelingsPrice",
            "description": "<p>装修费用</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "loanAmount",
            "description": "<p>贷款金额</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "advantage",
            "description": "<p>优劣势</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "adaptability",
            "description": "<p>配合度</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "proposition",
            "description": "<p>销售建议</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "bonusPacks",
            "description": "<p>附加赠送</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "ownerClaim",
            "description": "<p>业主要求</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "warn",
            "description": "<p>特别提醒</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "replenish",
            "description": "<p>补充</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "fulls",
            "description": "<p>满几年</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "sole",
            "description": "<p>唯一</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "account",
            "description": "<p>户口状况</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "owned",
            "description": "<p>是否共有</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseCase",
            "description": "<p>房屋状况</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "decorateCase",
            "description": "<p>装修状况</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "decorateType",
            "description": "<p>装修类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "decorateTime",
            "description": "<p>装修时间(格式：yyyy-MM-dd)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "schoolHouseNumber",
            "description": "<p>学区房名额</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "keyes",
            "description": "<p>是否能留钥匙</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "lookHouseTime",
            "description": "<p>看房时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "certificateComplete",
            "description": "<p>两证是否齐全</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "equityNum",
            "description": "<p>产权共有人数</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "sign",
            "description": "<p>签约当日能否到场</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "loan",
            "description": "<p>是否有贷款</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "loanPrice",
            "description": "<p>贷款金额</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "inHouse",
            "description": "<p>户口是否在本房屋内</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "give",
            "description": "<p>家具家电是否赠送</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "carport",
            "description": "<p>是否有车位</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseOccupy",
            "description": "<p>学区房是否被占用</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "occupyTime",
            "description": "<p>已占用多久</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "expectTime",
            "description": "<p>预期售房时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "fullFive",
            "description": "<p>是否满五唯一</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "payWay",
            "description": "<p>付款方式有何要求</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "maintainId",
            "description": "<p>维护信息id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>类型（0可上学校1看房时间）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "taxType",
            "description": "<p>税种</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "taxRate",
            "description": "<p>税率</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "taxPrice",
            "description": "<p>税费</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/zcy/addMaintain"
      }
    ],
    "group": "ZCY",
    "name": "zcy_addMaintain",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/ZcyController.java",
    "groupTitle": "ZCY"
  },
  {
    "type": "post",
    "url": "zcy/addOwner",
    "title": "添加业主信息",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "ZcyOwnerContacts",
            "optional": false,
            "field": "zcyOwnerContactses",
            "description": "<p>业主信息联系方式集合</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": ""
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "estatesId",
            "description": "<p>资产信息id</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "sellPrice",
            "description": "<p>出售价格(单位：万）</p>"
          },
          {
            "group": "Parameter",
            "type": "double",
            "optional": false,
            "field": "ownerPrice",
            "description": "<p>业主净得价(单位：万)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "entrustProtocolTime",
            "description": "<p>委托协议时间(格式：yyyy-MM-dd)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "entrustAbortTime",
            "description": "<p>委托截止时间(格式：yyyy-MM-dd)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "annotationName",
            "description": "<p>注释名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "annotationContent",
            "description": "<p>注释内容</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "ownerNumber",
            "description": "<p>业主编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "entrustSource",
            "description": "<p>委托来源</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "entrustDetail",
            "description": "<p>详细来源</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "ownerMariage",
            "description": "<p>业主婚姻</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "specialHouse",
            "description": "<p>特殊房源</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "ownerId",
            "description": "<p>业主信息id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>联系人</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "type",
            "description": "<p>联系人类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "sex",
            "description": "<p>性别</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "phone",
            "description": "<p>号码</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "phoneType",
            "description": "<p>电话类型（手机或固定电话）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "email",
            "description": "<p>邮箱</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/zcy/addOwner"
      }
    ],
    "group": "ZCY",
    "name": "zcy_addOwner",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/ZcyController.java",
    "groupTitle": "ZCY"
  },
  {
    "type": "post",
    "url": "zcy/awaitReceive",
    "title": "资产源列表",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/zcy/awaitReceive"
      }
    ],
    "group": "ZCY",
    "name": "zcy_awaitReceive",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"count\":12,//总数量\n\"zcyPawnDTOs\":\n{\n\"pawnId\":\"\",//抵押物id\n\"estatesId\":\"\",//资产信息id\n\"keyId\":\"\",// 钥匙信息id\n\"title\":\"\",//标题\n\"houseNo\":\"\",//编号\n\"place\":\"\",//位置\n\"label\":\"\",//标签\n\"houseType\":\"\",//户型\n\"orientation\":\"\",//朝向\n\"floor\":\"\",//楼层\n\"acreage\":\"\",//面积\n\"priceTotal\":\"\",//总价（万）\n\"priceUnit\":\"\",//单价(万)\n\"hangShingle\":\"\",//挂牌\n\"daiKan\":\"\",//带看\n\"daiKanLately \":\"\",//最近带看\n\"keKan\":\"\",//可看\n\"shiKan\":\"\",//实勘\n\"maintaining\":\"\",//维护人\n\"currentBooking\":\"\",//当前预约\n\"realityDynamic\":\"\",//实际动态\n\"entrustTime\":\"\"//委托时间\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/ZcyController.java",
    "groupTitle": "ZCY",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>状态（0待接收13待分配2正在处置99全部）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houses",
            "description": "<p>楼盘小区名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "buildingNo",
            "description": "<p>楼栋</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "startPriceTotal",
            "description": "<p>总价（起始）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "endPriceTotal",
            "description": "<p>总价（结束）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseNo",
            "description": "<p>房源编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseS",
            "description": "<p>室（居室）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseT",
            "description": "<p>厅（居室）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "startFloor",
            "description": "<p>实际楼层（起始）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "endFloor",
            "description": "<p>实际楼层（结束）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "startDecade",
            "description": "<p>建筑年代（起始）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "endDecade",
            "description": "<p>建筑年代（结束）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "startAcreage",
            "description": "<p>面积（起始）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "endAcreage",
            "description": "<p>面积（结束）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseBelong",
            "description": "<p>房屋权属</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "buildType",
            "description": "<p>建筑类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "maintaining",
            "description": "<p>维护人</p>"
          },
          {
            "group": "Parameter",
            "type": "string[]",
            "optional": false,
            "field": "orientationList",
            "description": "<p>朝向（集合形式）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseUse",
            "description": "<p>房屋用途</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "houseCase",
            "description": "<p>房屋现状</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "startEntrust",
            "description": "<p>挂牌时间（起始）</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "endEntrust",
            "description": "<p>挂牌时间（结束）</p>"
          },
          {
            "group": "Parameter",
            "type": "string[]",
            "optional": false,
            "field": "facilityList",
            "description": "<p>标签集合</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "zcy/get",
    "title": "获取信息",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "id",
            "description": "<p>资产信息id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "type",
            "description": "<p>请求类型（1资产信息2业主信息3维护信息4实勘信息5钥匙信息6速卖信息7证件消息）</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/zcy/get"
      }
    ],
    "group": "ZCY",
    "name": "zcy_get",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"estates\":{},//资产源信息\n\"address\":{},//房源地址\n\"facility\":{},//配套设施\n\"feature\":{},//房源特色\n\"defect\":{},//房源缺点\n\"contacts\":{},//联系人\n\"owner\":{},//业主信息\n\"maintain\":{},//维护信息\n\"tax\":{},//税率\n\"school\":{},//可上学校\n\"lookHouse\":{},//看房时间;\n\"key\":{},//钥匙信息\n\"express\":{}//速卖信息\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/ZcyController.java",
    "groupTitle": "ZCY"
  },
  {
    "type": "post",
    "url": "zcy/receivePawn",
    "title": "接收抵押物",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>状态（0接收1拒绝）</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/zcy/receivePawn"
      }
    ],
    "group": "ZCY",
    "name": "zcy_receivePawn",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/ZcyController.java",
    "groupTitle": "ZCY"
  },
  {
    "type": "post",
    "url": "zcy/zcyDetail",
    "title": "资产源详情",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "estatesId",
            "description": "<p>资产源id</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/zcy/zcyDetail"
      }
    ],
    "group": "ZCY",
    "name": "zcy_zcyDetail",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"result\": \"yes\",\n\"zcyPawnDTO\": {\n\"pawnId\": 16,//抵押物id\n\"estatesId\": 6,//资产信息id\n\"keyId\": 3,钥匙信息id\n\"title\": \"1\",//标题\n\"houseNo\": \"131345\",//编号\n\"place\": \"11111111111\",//位置\n\"label\": [],//标签\n\"houseType\": \"131-31-33-131\",//户型\n\"orientation\": \"3\",//朝向\n\"floor\": null,//楼层\n\"acreage\": \"313.00\",//面积\n\"priceTotal\": \"5.00\",//总价（万）\n\"priceUnit\": \"13.00\",//单价(万)\n\"hangShingle\": \"0\",//挂牌\n\"daiKan\": 0,//带看\n\"daiKanLately\": 0,//最近带看\n\"keKan\": 0,//可看\n\"shiKan\": 0,\n\"maintaining\": \"\",//维护人\n\"currentBooking\": 0,\n\"realityDynamic\": 0,\n\"entrustTime\": null //委托时间\n},\n\"detail\": {\n\"area\": \"11\",//市区\n\"decade\": \"2016-09-26\",//年代\n\"buildType\": \"6\",//建筑类型\n\"heatingWay\": \"1\",//供暖方式\n\"acreage\": 313,//面积\n\"guidePrice\": 13,//过户指导价(元/m2)\n\"houseBelong\": \"4\",//房屋权属\n\"tenementPrice\": 1,//物业费\n\"facility\": \"31\",//嫌恶设施\n\"houseUse\": \"65\"， //房屋用途\n\"originalPrice\": ，//原购房价格（单位：万）\n\"entrustSource\":,//委托来源\n\"expressPrice\":,//速卖价格\n\"expressStartTime\":,//速卖起始时间\n\"expressEndTime\":,//速卖结束时间\n\"entrustType\":\"出售\",//委托类型\n\"decorateCase\":\"精装\",//装修情况\n\"metro\":1//距离铁\n}\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/ZcyController.java",
    "groupTitle": "ZCY"
  },
  {
    "type": "post",
    "url": "/announcement/add",
    "title": "添加公告",
    "name": "add",
    "group": "announcement",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>添加后的数据id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AnnouncementApidoc.java",
    "groupTitle": "announcement",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//announcement/add"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>数据id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "ids",
            "description": "<p>被授权人Id集合，“,”分隔</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "content",
            "description": "<p>富文本内容</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "cover",
            "description": "<p>封面图片路径</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isCover",
            "description": "<p>封面图片是否显示在正文中(1显示，0不显示)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "mark",
            "description": "<p>摘要</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "/announcement/delete",
    "title": "删除公告",
    "name": "delete",
    "group": "announcement",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>公告id</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>返回删除条数</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AnnouncementApidoc.java",
    "groupTitle": "announcement",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//announcement/delete"
      }
    ]
  },
  {
    "type": "get",
    "url": "/announcement/get",
    "title": "根据ID获取公告",
    "name": "get",
    "group": "announcement",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>数据id</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "AnnouncementDTO",
            "optional": false,
            "field": "data",
            "description": "<p>公告内容</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "AnnouncementDTO",
          "content": "{\n    \"id\": 1,\n    \"ids\": \"12,18,229\",\n    \"content\": \"测试正文内容\",\n    \"cover\": \"/asd/asd.img\",\n    \"isCover\": 0,\n    \"mark\": \"摘要\",\n    \"createAt\": \"2016-09-26\",\n    \"stateFlag\": 0\n}\ngit: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_orm/src/main/java/com/dqys/business/orm/pojo/common/Announcement.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AnnouncementApidoc.java",
    "groupTitle": "announcement",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//announcement/get"
      }
    ]
  },
  {
    "type": "get",
    "url": "/announcement/list",
    "title": "获取当前用户所有公告",
    "name": "list",
    "group": "announcement",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "AnnouncementDTO",
            "optional": false,
            "field": "data",
            "description": "<p>公告集合</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "AnnouncementDTO",
          "content": "{\n    \"id\": 1,\n    \"ids\": \"12,18,229\",\n    \"content\": \"测试正文内容\",\n    \"cover\": \"/asd/asd.img\",\n    \"isCover\": 0,\n    \"mark\": \"摘要\",\n    \"createAt\": \"2016-09-26\",\n    \"stateFlag\": 0\n}\ngit: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_orm/src/main/java/com/dqys/business/orm/pojo/common/Announcement.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AnnouncementApidoc.java",
    "groupTitle": "announcement",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//announcement/list"
      }
    ]
  },
  {
    "type": "POST",
    "url": "/asset/add",
    "title": "添加资产包",
    "name": "add",
    "group": "asset",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>新增的数据ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AssetApidoc.java",
    "groupTitle": "asset",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//asset/add"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>主键</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>资产包类型</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "startAt",
            "description": "<p>委托开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "endAt",
            "description": "<p>委托结束时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "assetNo",
            "description": "<p>资产包编号</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "accrual",
            "description": "<p>总利息</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "loan",
            "description": "<p>总贷款</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "appraisal",
            "description": "<p>总评估</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>名臣</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateExcellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateLevel",
            "description": "<p>评级</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "province",
            "description": "<p>省份</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "city",
            "description": "<p>城市</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "district",
            "description": "<p>区域</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "address",
            "description": "<p>详细地址</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "loanOrganization",
            "description": "<p>贷款机构</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "loanOrganizationDistrict",
            "description": "<p>贷款机构行政区域</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "disposeMode",
            "description": "<p>处置方式</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "tags",
            "description": "<p>标签</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isshow",
            "description": "<p>是否展示</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "operatorId",
            "description": "<p>操作人ID</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "/asset/addLender",
    "title": "添加资产包借款人(整合版)",
    "name": "addLender",
    "group": "asset",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>资产包ID</p>"
          },
          {
            "group": "Parameter",
            "type": "LenderDTO",
            "optional": false,
            "field": "lenderDTO",
            "description": "<p>借款人基础信息</p>"
          },
          {
            "group": "Parameter",
            "type": "ContactDTO",
            "optional": false,
            "field": "contactDTOList",
            "description": "<p>联系人集合</p>"
          },
          {
            "group": "Parameter",
            "type": "PawnDTO",
            "optional": false,
            "field": "pawnDTOList",
            "description": "<p>抵押物集合</p>"
          },
          {
            "group": "Parameter",
            "type": "IouDTO",
            "optional": false,
            "field": "iouDTOList",
            "description": "<p>借据集合</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "iouNo",
            "description": "<p>编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "iouName",
            "description": "<p>借据名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "type",
            "description": "<p>借据类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "agency",
            "description": "<p>代理机构</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "loanTime",
            "description": "<p>放款时间</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "loanAtTime",
            "description": "<p>到款时间</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "amount",
            "description": "<p>金额</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "pactRate",
            "description": "<p>合同利率</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "outtimeMultiple",
            "description": "<p>逾期倍数</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "appropriationMultiple",
            "description": "<p>挪用倍数</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "accrualRepay",
            "description": "<p>已还利息金额</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "loanRepay",
            "description": "<p>已还贷款金额</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "levelType",
            "description": "<p>5级分类</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "outDays",
            "description": "<p>逾期天数</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "lessCorpus",
            "description": "<p>剩余本金</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "accrualArrears",
            "description": "<p>拖欠利息</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "penalty",
            "description": "<p>罚息</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "arrears",
            "description": "<p>欠款合计</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "endAt",
            "description": "<p>欠款截止日期</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "worth",
            "description": "<p>价值</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "advanceCorpus",
            "description": "<p>提前偿还本金</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateExcellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateLevel",
            "description": "<p>评级</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lenderId",
            "description": "<p>借款基础信息</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnIds",
            "description": "<p>抵押物IDs</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnNames",
            "description": "<p>抵押物名称集合</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnNo",
            "description": "<p>抵押物编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnName",
            "description": "<p>抵押物名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "size",
            "description": "<p>规模大小</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "province",
            "description": "<p>省份</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "city",
            "description": "<p>城市</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "district",
            "description": "<p>区域</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "address",
            "description": "<p>详细地址</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "pawnRate",
            "description": "<p>抵押率</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "disposeStatus",
            "description": "<p>处置状态</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "iouIds",
            "description": "<p>借据Id集合</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AssetApidoc.java",
    "groupTitle": "asset",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//asset/addLender"
      }
    ],
    "success": {
      "examples": [
        {
          "title": "lenderDTO:",
          "content": "{\n\"id\": 1,\n\"assetId\": 1,\n\"startAt\": \"2016-07-06\",\n\"endAt\": \"2016-09-06\",\n\"accrual\": null,\n\"loan\": null,\n\"appraisal\": null,\n\"loanType\": null,\n\"loanMode\": null,\n\"loanName\": null,\n\"evaluateExcellent\": null,\n\"evaluateLevel\": null,\n\"disposeMode\": null,\n\"tags\": null,\n\"urgeType\": null,\n\"entrustBornType\": null,\n\"entrustBorn\": null,\n\"guaranteeType\": null,\n\"guaranteeMode\": null,\n\"guaranteeSource\": null,\n\"isGuaranteeConnection\": null,\n\"guarenteeEconomic\": null,\n\"isLawsuit\": null,\n\"isDecision\": null,\n\"realUrgeTime\": null,\n\"phoneUrgeTime\": null,\n\"entrustUrgeTime\": null,\n\"canContact\": null,\n\"canPay\": null,\n\"isWorth\": null,\n\"memo\": null,\n\"operatorId\": 0,\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/LenderDTO.java",
          "type": "json"
        },
        {
          "title": "ContactDTO:",
          "content": "{\n    \"id\": 917,\n    \"name\": \"张飞\",\n    \"type\": 1,\n    \"idcard\": \"452722197507190776\",\n    \"mobile\": \"13855552225\",\n    \"mode\": \"11\",\n    \"modeId\": 245,\n    \"avg\": null,\n    \"gender\": 1,\n    \"company\": \"尴尬啊啊\",\n    \"homeTel\": \"05714444222\",\n    \"officeTel\": \"05713333222\",\n    \"email\": null,\n    \"province\": \"12\",\n    \"city\": \"1202\",\n    \"district\": \"120221\",\n    \"address\": \"嘎嘎嘎\",\n    \"memo\": null,\n    \"code\": null,\n    \"otherAddress\": \"[]\"\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/ContactDTO.java",
          "type": "json"
        }
      ]
    }
  },
  {
    "type": "get",
    "url": "/asset/delete",
    "title": "删除资产包",
    "name": "delete",
    "group": "asset",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>资产包ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AssetApidoc.java",
    "groupTitle": "asset",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//asset/delete"
      }
    ]
  },
  {
    "type": "get",
    "url": "/asset/excelIn",
    "title": "excel导入资产包的借款人",
    "name": "excelIn",
    "group": "asset",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>公司ID</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "file",
            "description": "<p>excel文件</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AssetApidoc.java",
    "groupTitle": "asset",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//asset/excelIn"
      }
    ]
  },
  {
    "type": "get",
    "url": "/asset/get",
    "title": "获取资产包",
    "name": "get",
    "group": "asset",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>资产包ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "AssetDTO",
            "optional": false,
            "field": "data",
            "description": "<p>资产包信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "AssetDTO:",
          "content": "{\n\"id\": 54,\n\"type\": null,\n\"startAt\": null,\n\"endAt\": null,\n\"assetNo\": \"QS1607072449\",\n\"accrual\": null,\n\"loan\": null,\n\"appraisal\": null,\n\"name\": null,\n\"evaluateExcellent\": null,\n\"evaluateLevel\": null,\n\"province\": null,\n\"city\": null,\n\"district\": null,\n\"address\": null,\n\"loanOrganization\": null,\n\"loanOrganizationDistrict\": null,\n\"disposeMode\": null,\n\"tags\": null,\n\"isshow\": 0,\n\"memo\": null,\n\"operatorId\": 0,\n\"operator\": null\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/AssetDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AssetApidoc.java",
    "groupTitle": "asset",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//asset/get"
      }
    ]
  },
  {
    "type": "get",
    "url": "/asset/getInit",
    "title": "获取初始化数据",
    "name": "getInit",
    "group": "asset",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "SelectonDTO",
            "optional": false,
            "field": "assetType",
            "description": "<p>资产包类型</p>"
          },
          {
            "group": "Success 200",
            "type": "SelectonDTO",
            "optional": false,
            "field": "excellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Success 200",
            "type": "string",
            "optional": false,
            "field": "level",
            "description": "<p>评级</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "SelectDTO-Success-Response:",
          "content": "{\n    key:key,\n    value:\"value\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AssetApidoc.java",
    "groupTitle": "asset",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//asset/getInit"
      }
    ]
  },
  {
    "type": "get",
    "url": "/asset/list",
    "title": "获取资产包列表",
    "name": "list",
    "group": "asset",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "nav",
            "description": "<p>子导航栏项目</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "type",
            "description": "<p>资产包类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "areaId",
            "description": "<p>行政区域ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "operator",
            "description": "<p>操作人Id</p>"
          },
          {
            "group": "Parameter",
            "type": "boolean",
            "optional": true,
            "field": "isOwn",
            "description": "<p>是否选择自己录入选项</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "companyId",
            "description": "<p>贷款公司Id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "code",
            "description": "<p>编号</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": true,
            "field": "startAt",
            "description": "<p>开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": true,
            "field": "endAt",
            "description": "<p>结束时间</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "AssetDTO",
            "optional": false,
            "field": "data",
            "description": "<p>资产包信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "AssetDTO:",
          "content": "{\n\"id\": 54,\n\"type\": null,\n\"startAt\": null,\n\"endAt\": null,\n\"assetNo\": \"QS1607072449\",\n\"accrual\": null,\n\"loan\": null,\n\"appraisal\": null,\n\"name\": null,\n\"evaluateExcellent\": null,\n\"evaluateLevel\": null,\n\"province\": null,\n\"city\": null,\n\"district\": null,\n\"address\": null,\n\"loanOrganization\": null,\n\"loanOrganizationDistrict\": null,\n\"disposeMode\": null,\n\"tags\": null,\n\"isshow\": 0,\n\"memo\": null,\n\"operatorId\": 0,\n\"operator\": null\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/AssetDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AssetApidoc.java",
    "groupTitle": "asset",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//asset/list"
      }
    ]
  },
  {
    "type": "get",
    "url": "/asset/listLender",
    "title": "查询资产包借款人",
    "name": "listLender",
    "group": "asset",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>资产包ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "LenderListDTO",
            "optional": false,
            "field": "data",
            "description": "<p>借款人相关信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "lenderListDTO:",
          "content": "{\n    \"id\": 235,\n    \"name\": \"借款人\",\n    \"type\": \"借款人\",\n    \"born\": \"类型-1\",\n    \"assetName\": \"委托方资产包\",\n    \"excellent\": \"非常好\",\n    \"level\": \"B\",\n    \"dispose\": \"处置方式\",\n    \"tag\": null,\n    \"isLawsuit\": \"是\",\n    \"guaranteeType\": null,\n    \"guaranteeMode\": null,\n    \"guaranteeSource\": null,\n    \"guaranteeEconomic\": null,\n    \"guaranteeContact\": \"是\",\n    \"isWorth\": \"是\",\n    \"isLawsuit\": \"是\",\n    \"isDecision\": \"是\",\n    \"realUrgeTime\": 1,\n    \"phoneUrgeTime\": null,\n    \"entrustTime\": null,\n    \"canContact\": \"是\",\n    \"canPay\": \"是\",\n    \"memo\": null\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/LenderListDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AssetApidoc.java",
    "groupTitle": "asset",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//asset/listLender"
      }
    ]
  },
  {
    "type": "get",
    "url": "/asset/listLenderSelect",
    "title": "获取资产包下联系人下拉",
    "name": "listLenderSelect",
    "group": "asset",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>资产包ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "SelectonDTO",
            "optional": false,
            "field": "data",
            "description": "<p>借款人信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "SelectDTO-Success-Response:",
          "content": "{\n    key:key,\n    value:\"value\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AssetApidoc.java",
    "groupTitle": "asset",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//asset/listLenderSelect"
      }
    ]
  },
  {
    "type": "POST",
    "url": "/asset/update",
    "title": "修改资产包",
    "name": "update",
    "group": "asset",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>修改后的数据ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/AssetApidoc.java",
    "groupTitle": "asset",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//asset/update"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>主键</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>资产包类型</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "startAt",
            "description": "<p>委托开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "endAt",
            "description": "<p>委托结束时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "assetNo",
            "description": "<p>资产包编号</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "accrual",
            "description": "<p>总利息</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "loan",
            "description": "<p>总贷款</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "appraisal",
            "description": "<p>总评估</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>名臣</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateExcellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateLevel",
            "description": "<p>评级</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "province",
            "description": "<p>省份</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "city",
            "description": "<p>城市</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "district",
            "description": "<p>区域</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "address",
            "description": "<p>详细地址</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "loanOrganization",
            "description": "<p>贷款机构</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "loanOrganizationDistrict",
            "description": "<p>贷款机构行政区域</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "disposeMode",
            "description": "<p>处置方式</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "tags",
            "description": "<p>标签</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isshow",
            "description": "<p>是否展示</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "operatorId",
            "description": "<p>操作人ID</p>"
          }
        ]
      }
    }
  },
  {
    "type": "GET",
    "url": "http://{url}/b_log/list",
    "title": "查看日志列表",
    "name": "list",
    "group": "businessLog",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "不分页：\n{\n    \"code\": 2000,\n    \"msg\": \"成功\",\n    \"data\": [\n    {\n    \"id\": 853,\n    \"version\": null,\n    \"stateflag\": null,\n    \"createAt\": \"2016-10-21\",\n    \"updateAt\": null,\n    \"remark\": \"\",\n    \"objectType\": 11,\n    \"objectId\": null,\n    \"operType\": null,\n    \"userId\": null,\n    \"time\": null,\n    \"text\": \"协作删除用户\",\n    \"teamId\": null,\n    \"businessId\": null,\n    \"objectShowName\": \"借款人操作BO16100922\",\n    \"userName\": \"朱明\",\n    \"objectNo\": \"BO16100922\",\n    \"operName\": \"协作删除用户\",\n    \"objectName\": \"借款人操作\"\n    }］｝\n     分页：\n    {\n    \"code\": 2000,\n    \"msg\": \"成功\",\n    \"data\": {\n    \"total\": 21,\n    \"data\": [\n    {\n    \"id\": 853,\n    \"version\": null,\n    \"stateflag\": null,\n    \"createAt\": \"2016-10-21\",\n    \"updateAt\": null,\n    \"remark\": \"\",\n    \"objectType\": 11,\n    \"objectId\": null,\n    \"operType\": null,\n    \"userId\": null,\n    \"time\": null,\n    \"text\": \"协作删除用户\",\n    \"teamId\": null,\n    \"businessId\": null,\n    \"objectShowName\": \"借款人操作BO16100922\",\n    \"userName\": \"朱明\",\n    \"objectNo\": \"BO16100922\",\n    \"operName\": \"协作删除用户\",\n    \"objectName\": \"借款人操作\"\n    },.....]}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/BusinessLogController.java",
    "groupTitle": "businessLog",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/b_log/list"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "objectType",
            "description": "<p>objectType　对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "operType",
            "description": "<p>operType 操作类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "userId",
            "description": "<p>userId　操作人员id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "teamId",
            "description": "<p>teamId　协作团队</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "businessId",
            "description": "<p>businessId　业务号</p>"
          },
          {
            "group": "Parameter",
            "type": "boolean",
            "optional": true,
            "field": "isPaging",
            "description": "<p>isPaging　是否分页0分页，1不分页</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "startPageNum",
            "description": "<p>startPageNum　当前分页</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "pageSize",
            "description": "<p>pageSize　分页大小</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": true,
            "field": "createAt",
            "description": "<p>createAt　创建时间</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": true,
            "field": "beginDate",
            "description": "<p>beginDate　开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": true,
            "field": "endDate",
            "description": "<p>endDate　结束时间</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": true,
            "field": "searchText",
            "description": "<p>searchText　操作者或者内容</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "/case/add",
    "title": "创建案件信息",
    "name": "add",
    "group": "case",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>新增后的ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/add"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>主键ID(修改时必传,增加时可不传)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "pawnId",
            "description": "<p>抵押物ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "iouIds",
            "description": "<p>借据Ids(逗号隔开)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "pId",
            "description": "<p>被拆分的案件特有属性</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "plaintiff",
            "description": "<p>原告</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "defendant",
            "description": "<p>被告</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "spouse",
            "description": "<p>配偶</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mortgagor",
            "description": "<p>抵押人名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mortgageTime",
            "description": "<p>抵押次数</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "guarantor",
            "description": "<p>保证人名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateExcellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateLevel",
            "description": "<p>评级</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lawsuitAmount",
            "description": "<p>诉讼金额</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lawsuitCorpus",
            "description": "<p>诉讼本金</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lawsuitAccrual",
            "description": "<p>诉讼利息</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "attachmentStatus",
            "description": "<p>查封(1为肯定,表示已查封,下同)</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "attachmentDate",
            "description": "<p>查封时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "attachmentCourt",
            "description": "<p>法院</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "attachmentTime",
            "description": "<p>查封次数</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "preservation",
            "description": "<p>保全(1保全)</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "preservationStart",
            "description": "<p>保全开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "preservationMemo",
            "description": "<p>续保情况</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isFirst",
            "description": "<p>首封(1)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "firstAttachmentCourt",
            "description": "<p>首封法院</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "preservationCourt",
            "description": "<p>执行保全法院</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "firstAttachmentCode",
            "description": "<p>法院案号</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "firstAttachmentDate",
            "description": "<p>查封时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "lawsuitMemo",
            "description": "<p>诉讼备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "attachmentMemo",
            "description": "<p>查封情况</p>"
          },
          {
            "group": "Parameter",
            "type": "CaseCourt",
            "optional": false,
            "field": "courtDTOList",
            "description": "<p>相关联法院</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "caseId",
            "description": "<p>案件ID</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "court",
            "description": "<p>法院名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "code",
            "description": "<p>法院案号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "lawyer",
            "description": "<p>法官</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "gender",
            "description": "<p>性别(1男性)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "lawyerMemo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "tel",
            "description": "<p>座机</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "other",
            "description": "<p>其他联系方式</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "/case/delete",
    "title": "根据案件id删除",
    "name": "delete",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "id",
            "description": "<p>案件id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CaseController.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/delete"
      }
    ]
  },
  {
    "type": "post",
    "url": "/case/divide",
    "title": "拆分案件",
    "name": "divide",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>案件ID</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "ids",
            "description": "<p>借据ID集(&quot;,&quot;隔开)</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>拆分后的ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/divide"
      }
    ]
  },
  {
    "type": "post",
    "url": "/case/get",
    "title": "根据案件ID查询详细",
    "name": "get",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>案件ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "CaseDTO",
            "optional": false,
            "field": "data",
            "description": "<p>案件信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CaseDTO:",
          "content": "{}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseDTO.java",
          "type": "json"
        },
        {
          "title": "CaseCourtDTO:",
          "content": "{}\ngit : http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseCourtDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/get"
      }
    ]
  },
  {
    "type": "post",
    "url": "/case/list",
    "title": "根据借款人查询案件信息",
    "name": "list",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>借款人ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "index",
            "description": "<p>第N件案件</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "CaseDTO",
            "optional": false,
            "field": "data",
            "description": "<p>案件信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CaseDTO:",
          "content": "{}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseDTO.java",
          "type": "json"
        },
        {
          "title": "CaseCourtDTO:",
          "content": "{}\ngit : http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseCourtDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/list"
      }
    ]
  },
  {
    "type": "post",
    "url": "/case/listAdd",
    "title": "批量创建案件信息",
    "name": "listAdd",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "caseDTOList",
            "optional": false,
            "field": "caseDTOList",
            "description": "<p>参考创建案件信息</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>主键ID(修改时必传,增加时可不传)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "pawnId",
            "description": "<p>抵押物ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "iouIds",
            "description": "<p>借据Ids(逗号隔开)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "pId",
            "description": "<p>被拆分的案件特有属性</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "plaintiff",
            "description": "<p>原告</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "defendant",
            "description": "<p>被告</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "spouse",
            "description": "<p>配偶</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mortgagor",
            "description": "<p>抵押人名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mortgageTime",
            "description": "<p>抵押次数</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "guarantor",
            "description": "<p>保证人名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateExcellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateLevel",
            "description": "<p>评级</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lawsuitAmount",
            "description": "<p>诉讼金额</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lawsuitCorpus",
            "description": "<p>诉讼本金</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lawsuitAccrual",
            "description": "<p>诉讼利息</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "attachmentStatus",
            "description": "<p>查封(1为肯定,表示已查封,下同)</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "attachmentDate",
            "description": "<p>查封时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "attachmentCourt",
            "description": "<p>法院</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "attachmentTime",
            "description": "<p>查封次数</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "preservation",
            "description": "<p>保全(1保全)</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "preservationStart",
            "description": "<p>保全开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "preservationMemo",
            "description": "<p>续保情况</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isFirst",
            "description": "<p>首封(1)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "firstAttachmentCourt",
            "description": "<p>首封法院</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "preservationCourt",
            "description": "<p>执行保全法院</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "firstAttachmentCode",
            "description": "<p>法院案号</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "firstAttachmentDate",
            "description": "<p>查封时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "lawsuitMemo",
            "description": "<p>诉讼备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "attachmentMemo",
            "description": "<p>查封情况</p>"
          },
          {
            "group": "Parameter",
            "type": "CaseCourt",
            "optional": false,
            "field": "courtDTOList",
            "description": "<p>相关联法院</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "caseId",
            "description": "<p>案件ID</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "court",
            "description": "<p>法院名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "code",
            "description": "<p>法院案号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "lawyer",
            "description": "<p>法官</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "gender",
            "description": "<p>性别(1男性)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "lawyerMemo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "tel",
            "description": "<p>座机</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "other",
            "description": "<p>其他联系方式</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/listAdd"
      }
    ]
  },
  {
    "type": "post",
    "url": "/case/listByCase",
    "title": "根据母案件ID查询子案件",
    "name": "listByCase",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>母案件ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "CaseDTO",
            "optional": false,
            "field": "data",
            "description": "<p>案件信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CaseDTO:",
          "content": "{}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseDTO.java",
          "type": "json"
        },
        {
          "title": "CaseCourtDTO:",
          "content": "{}\ngit : http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseCourtDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/listByCase"
      }
    ]
  },
  {
    "type": "post",
    "url": "/case/listByCase",
    "title": "根据母案件查询子案件",
    "name": "listByCase",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>案件ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "CaseDTO",
            "optional": false,
            "field": "data",
            "description": "<p>子案件信息集合</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CaseDTO:",
          "content": "{}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseDTO.java",
          "type": "json"
        },
        {
          "title": "CaseCourtDTO:",
          "content": "{}\ngit : http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseCourtDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/listByCase"
      }
    ]
  },
  {
    "type": "post",
    "url": "/case/listByLender",
    "title": "根据借款人查询案件",
    "name": "listByLender",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>借款人ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "CaseDTO",
            "optional": false,
            "field": "data",
            "description": "<p>案件信息集合</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CaseDTO:",
          "content": "{}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseDTO.java",
          "type": "json"
        },
        {
          "title": "CaseCourtDTO:",
          "content": "{}\ngit : http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseCourtDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/listByLender"
      }
    ]
  },
  {
    "type": "post",
    "url": "/case/listByLender",
    "title": "根据借款人查询母案件信息",
    "name": "listByLender",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>案件ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "CaseDTO",
            "optional": false,
            "field": "data",
            "description": "<p>案件信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CaseDTO:",
          "content": "{}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseDTO.java",
          "type": "json"
        },
        {
          "title": "CaseCourtDTO:",
          "content": "{}\ngit : http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseCourtDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/listByLender"
      }
    ]
  },
  {
    "type": "post",
    "url": "/case/listCase",
    "title": "根据案件查询子案件（单条）",
    "name": "listCase",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>案件ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "index",
            "description": "<p>第N件子案件</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "CaseDTO",
            "optional": false,
            "field": "data",
            "description": "<p>案件信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CaseDTO:",
          "content": "{}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseDTO.java",
          "type": "json"
        },
        {
          "title": "CaseCourtDTO:",
          "content": "{}\ngit : http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseCourtDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/listCase"
      }
    ]
  },
  {
    "type": "post",
    "url": "/case/listIouByCase",
    "title": "查询案件的借据",
    "name": "listIouByCase",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>案件ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "SelectDTO",
            "optional": false,
            "field": "data",
            "description": "<p>key为案件ID，value为借据名称 //     * @apiUse SelectDTO</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/listIouByCase"
      }
    ]
  },
  {
    "type": "post",
    "url": "/case/process",
    "title": "记录过程",
    "name": "process",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "id",
            "description": "<p>案件id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "firstStair",
            "description": "<p>第一个</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "secondStait",
            "description": "<p>第二个</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CaseController.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/process"
      }
    ]
  },
  {
    "type": "post",
    "url": "/case/update",
    "title": "修改案件信息",
    "name": "update",
    "group": "case",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>修改后的ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/update"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>主键ID(修改时必传,增加时可不传)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "pawnId",
            "description": "<p>抵押物ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "iouIds",
            "description": "<p>借据Ids(逗号隔开)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "pId",
            "description": "<p>被拆分的案件特有属性</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "plaintiff",
            "description": "<p>原告</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "defendant",
            "description": "<p>被告</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "spouse",
            "description": "<p>配偶</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mortgagor",
            "description": "<p>抵押人名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mortgageTime",
            "description": "<p>抵押次数</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "guarantor",
            "description": "<p>保证人名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateExcellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateLevel",
            "description": "<p>评级</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lawsuitAmount",
            "description": "<p>诉讼金额</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lawsuitCorpus",
            "description": "<p>诉讼本金</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lawsuitAccrual",
            "description": "<p>诉讼利息</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "attachmentStatus",
            "description": "<p>查封(1为肯定,表示已查封,下同)</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "attachmentDate",
            "description": "<p>查封时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "attachmentCourt",
            "description": "<p>法院</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "attachmentTime",
            "description": "<p>查封次数</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "preservation",
            "description": "<p>保全(1保全)</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "preservationStart",
            "description": "<p>保全开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "preservationMemo",
            "description": "<p>续保情况</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isFirst",
            "description": "<p>首封(1)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "firstAttachmentCourt",
            "description": "<p>首封法院</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "preservationCourt",
            "description": "<p>执行保全法院</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "firstAttachmentCode",
            "description": "<p>法院案号</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "firstAttachmentDate",
            "description": "<p>查封时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "lawsuitMemo",
            "description": "<p>诉讼备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "attachmentMemo",
            "description": "<p>查封情况</p>"
          },
          {
            "group": "Parameter",
            "type": "CaseCourt",
            "optional": false,
            "field": "courtDTOList",
            "description": "<p>相关联法院</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "caseId",
            "description": "<p>案件ID</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "court",
            "description": "<p>法院名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "code",
            "description": "<p>法院案号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "lawyer",
            "description": "<p>法官</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "gender",
            "description": "<p>性别(1男性)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "lawyerMemo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mobile",
            "description": "<p>手机</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "tel",
            "description": "<p>座机</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "other",
            "description": "<p>其他联系方式</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "/case/updateCaseAttachment",
    "title": "修改案件查封保全信息",
    "name": "updateCaseAttachment",
    "group": "case",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>修改结果</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/updateCaseAttachment"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>借款人Id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "attachmentStatus",
            "description": "<p>查封(1表示已查封)</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "attachmentDate",
            "description": "<p>查封时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "attachmentCourt",
            "description": "<p>法院</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "attachmentTime",
            "description": "<p>查封次数</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "preservation",
            "description": "<p>保全(1保全)</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "preservationStart",
            "description": "<p>保全开始时间</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "preservationEnd",
            "description": "<p>保全结束时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "preservationMemo",
            "description": "<p>续保情况</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isFirst",
            "description": "<p>首封(1)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "preservationCourt",
            "description": "<p>执行保全法院</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "firstAttachmentCode",
            "description": "<p>法院案号</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "firstAttachmentDate",
            "description": "<p>查封时间</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "attachmentMemo",
            "description": "<p>查封情况</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "/case/updateCaseBase",
    "title": "修改案件基础信息",
    "name": "updateCaseBase",
    "group": "case",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>修改结果</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/updateCaseBase"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>案件ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "pawnId",
            "description": "<p>抵押物ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "iouIds",
            "description": "<p>借据Ids(逗号隔开)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "plaintiff",
            "description": "<p>原告</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "defendant",
            "description": "<p>被告</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "spouse",
            "description": "<p>配偶</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "mortgagor",
            "description": "<p>抵押人名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "guarantor",
            "description": "<p>保证人名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateExcellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateLevel",
            "description": "<p>评级</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "/case/updateCaseCourt",
    "title": "修改案件关联法院",
    "name": "updateCaseCourt",
    "group": "case",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>修改结果</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CaseCourtDTO:",
          "content": "{}\ngit : http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/cases/CaseCourtDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/updateCaseCourt"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>案件ID</p>"
          },
          {
            "group": "Parameter",
            "type": "CaseCourtDTO",
            "optional": false,
            "field": "caseCourtDTOList",
            "description": "<p>案件信息集合，详细参考下方DTO参数</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "/case/updateCaseLawsuit",
    "title": "修改案件诉讼信息",
    "name": "updateCaseLawsuit",
    "group": "case",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>修改结果</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/updateCaseLawsuit"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>案件ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lawsuitAmount",
            "description": "<p>诉讼金额</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lawsuitCorpus",
            "description": "<p>诉讼本金</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lawsuitAccrual",
            "description": "<p>诉讼利息</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "/case/updateCaseMemo",
    "title": "修改案件备注",
    "name": "updateCaseMemo",
    "group": "case",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>案件ID</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>备注信息</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>修改结果</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CaseApidoc.java",
    "groupTitle": "case",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//case/updateCaseMemo"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/company/addBusinessService",
    "title": "平台为申请业务流转的公司添加业务流转伙伴接口",
    "name": "addBusinessService",
    "group": "companyRelation",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>业务流转类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>公司ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "distributionId",
            "description": "<p>分配器ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "businessType",
            "description": "<p>业务流转类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "companyId",
            "description": "<p>被邀请公司ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>分配后的业务流转成员ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "companyRelation",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/addBusinessService"
      }
    ]
  },
  {
    "type": "post",
    "url": "coordinator/businessFlow",
    "title": "业务流转通知平台",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "flowId",
            "description": "<p>流转对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "flowType",
            "description": "<p>流转对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "operType",
            "description": "<p>操作类型（参考IouEnum 或PawnEnum）</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "companyTeamId",
            "description": "<p>分配器id</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/businessFlow"
      }
    ],
    "group": "companyRelation",
    "name": "coordinator_businessFlow",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "companyRelation"
  },
  {
    "type": "post",
    "url": "coordinator/businessFlowResult",
    "title": "平台审核业务流转通知",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "flowId",
            "description": "<p>业务流转对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "flowType",
            "description": "<p>业务流转对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "operType",
            "description": "<p>流转操作</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "receiveUserId",
            "description": "<p>接收者id（请求公司）</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "status",
            "description": "<p>状态（0拒绝1接收）</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": true,
            "field": "messageId",
            "description": "<p>消息id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": true,
            "field": "operStatus",
            "description": "<p>操作状态（0默认未操作1同意2拒绝）</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": true,
            "field": "inviteUserId",
            "description": "<p>被邀请用户id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "flowBusinessId",
            "description": "<p>业务流转状态id</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/coordinator/businessFlowResult"
      }
    ],
    "group": "companyRelation",
    "name": "coordinator_businessFlowResult",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/CoordinatorController.java",
    "groupTitle": "companyRelation"
  },
  {
    "type": "get",
    "url": "/api/company/designBusinessService",
    "title": "被添加公司接受或者拒绝业务流转邀请",
    "name": "designBusinessService",
    "group": "companyRelation",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>业务流转类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>公司ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "distributionId",
            "description": "<p>分配器ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "businessType",
            "description": "<p>业务流转类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "status",
            "description": "<p>接收1拒绝2</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>业务流转操作后的成员ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "companyRelation",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/designBusinessService"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/company/exitBusinessService",
    "title": "删除被添加公司的业务流转",
    "name": "exitBusinessService",
    "group": "companyRelation",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>分配器ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "targetType",
            "description": "<p>对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "targetId",
            "description": "<p>对象ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>空值返回值</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "companyRelation",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/exitBusinessService"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/company/getRelation",
    "title": "获取公司间合作关系",
    "name": "getRelation",
    "group": "companyRelation",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>公司ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "CompanyRelationDTO",
            "optional": false,
            "field": "data",
            "description": "<p>公司关系表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CompanyRelationDTO:",
          "content": "HTTP/1.1 2000 ok\n{\n\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/CompanyRelationDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "companyRelation",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/getRelation"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/company/listByService",
    "title": "根据业务类型获取公司",
    "name": "listByService",
    "group": "companyRelation",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>业务流转类型</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "CompanyDTO",
            "optional": false,
            "field": "data",
            "description": "<p>公司信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CompanyDTO-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    id:1,\n    name:'name',\n    province:'浙江省',\n    city:'杭州市',\n    district:'江干区'\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/CompanyDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "companyRelation",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/listByService"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/company/listRelationByService",
    "title": "根据业务类型获取公司联系关系",
    "name": "listRelationByService",
    "group": "companyRelation",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>业务流转类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>公司ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "CompanyDTO",
            "optional": false,
            "field": "data",
            "description": "<p>公司信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CompanyDTO-Response:",
          "content": "HTTP/1.1 2000 ok\n{\n    id:1,\n    name:'name',\n    province:'浙江省',\n    city:'杭州市',\n    district:'江干区'\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/CompanyDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "companyRelation",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/listRelationByService"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/company/designDistribution",
    "title": "决定加入分配器(同意或者拒绝)",
    "name": "designDistribution",
    "group": "distribution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>被邀请ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "status",
            "description": "<p>操作类型(接收1|拒绝0)</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>操作后的成员id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "distribution",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/designDistribution"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/company/exitDistribution",
    "title": "退出分配器",
    "name": "exitDistribution",
    "group": "distribution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>分配器成员ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": ""
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "distribution",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/exitDistribution"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/company/getDistribution",
    "title": "获取该对象的分配器列表",
    "name": "getDistribution",
    "group": "distribution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>分配对象类型(如:资产包asset)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>分配对象ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "DistributionDTO",
            "optional": false,
            "field": "data",
            "description": "<p>分配器列表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "CompanyTeamReDTO:",
          "content": "{\n    \"id\": 42,\n    \"avg\": null,\n    \"type\": \"委托方\",\n    \"address\": \"浙江省温州市乐清市\",\n    \"rate\": \"0\",\n    \"task\": 0,\n    \"contact\": \"ls\",\n    \"status\": 1,\n    \"time\": \"2016-09-20\",\n    \"stateflag\": 0\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/CompanyTeamReDTO.java",
          "type": "json"
        },
        {
          "title": "BusinessServiceDTO:",
          "content": "{\n    \"id\": 50,\n    \"avg\": null,\n    \"type\": \"32\",\n    \"name\": \"abc中介\",\n    \"address\": \"吉林省长春市长春市辖区\",\n    \"rate\": \"0/0\",\n    \"task\": 0,\n    \"target\": \"pawnname\",\n    \"time\": \"2016-09-21\",\n    \"stateflag\": null,\n    \"status\": 0\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/BusinessServiceDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "distribution",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/getDistribution"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/company/inviteDistribution",
    "title": "邀请加入分配器",
    "name": "inviteDistribution",
    "group": "distribution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>分配器ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "companyId",
            "description": "<p>公司ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>分配后的成员id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "distribution",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/inviteDistribution"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/company/joinDistribution",
    "title": "申请加入分配器",
    "name": "joinDistribution",
    "group": "distribution",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>分配器ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>分配后的成员id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "distribution",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/joinDistribution"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/follow_up/add",
    "title": "增加跟进信息,状态为未发送",
    "name": "add",
    "group": "followUp",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"id\": null,\n\"objectId\": 111,\n\"objectType\": 111,\n\"secondObjectId\": 11,\n\"secondObjectType\": 11,\n\"content\": \"11\",\n\"liquidateStage\": 11,\n\"secondLiquidateStage\": 11,\n\"fileList\": [\n{\n\"id\": null,\n\"version\": null,\n\"stateflag\": null,\n\"createAt\": null,\n\"updateAt\": null,\n\"remark\": null,\n\"pathFilename\": \"aaa.jpg\",\n\"showFilename\": \"p.jpg\",\n\"followUpMessageId\": 18\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/FollowUpController.java",
    "groupTitle": "followUp",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/follow_up/add"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "secondObjectId",
            "description": "<p>二级对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "secondObjectType",
            "description": "<p>二级对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>内容</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "liquidateStage",
            "description": "<p>清收阶段</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "secondLiquidateStage",
            "description": "<p>二级清收阶段</p>"
          },
          {
            "group": "Parameter",
            "type": "list",
            "optional": true,
            "field": "fileList",
            "description": "<p>文件名称列表</p>"
          }
        ]
      }
    }
  },
  {
    "type": "GET",
    "url": "http://{url}/follow_up/list",
    "title": "查询更进信息,并去除对应阶段的未读数据",
    "name": "list",
    "group": "followUp",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": [\n{\n\"id\": 18,\n\"version\": null,\n\"stateflag\": null,\n\"createAt\": \"2016-09-22\",\n\"updateAt\": null,\n\"remark\": null,\n\"objectId\": null,\n\"objectType\": null,\n\"userId\": null,\n\"teamId\": null,\n\"content\": \"11\",\n\"secondObjectId\": null,\n\"secondObjectType\": null,\n\"liquidateStage\": 11,\n\"secondLiquidateStage\": null,\n\"sendStatus\": null,\n\"userInfo\": {\n\"id\": null,\n\"version\": null,\n\"stateflag\": null,\n\"createAt\": null,\n\"updateAt\": null,\n\"remark\": null,\n\"userName\": \"zs\",\n\"realName\": null,\n\"account\": null,\n\"wechat\": null,\n\"avg\": null,\n\"sex\": true,\n\"mobile\": null,\n\"email\": null,\n\"password\": null,\n\"salt\": null,\n\"identity\": null,\n\"companyId\": null,\n\"status\": null,\n\"qq\": null\n},\n\"teammateRe\": null,\n\"companyInfo\": {\n\"id\": null,\n\"version\": null,\n\"stateflag\": null,\n\"createAt\": null,\n\"updateAt\": null,\n\"remark\": null,\n\"companyName\": \"test\",\n\"credential\": null,\n\"licence\": null,\n\"legalPerson\": null,\n\"province\": null,\n\"city\": null,\n\"area\": null,\n\"address\": null,\n\"isAuth\": null,\n\"type\": null\n},\n\"fileList\": [\n{\n\"id\": null,\n\"version\": null,\n\"stateflag\": null,\n\"createAt\": null,\n\"updateAt\": null,\n\"remark\": null,\n\"pathFilename\": \"aaa.jpg\",\n\"showFilename\": \"p.jpg\",\n\"followUpMessageId\": null\n}\n]\n}\n]\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/FollowUpController.java",
    "groupTitle": "followUp",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/follow_up/list"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "objectType",
            "description": "<p>对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "teamId",
            "description": "<p>团队id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>内容</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "secondObjectId",
            "description": "<p>二级对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "secondObjectType",
            "description": "<p>二级对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "liquidateStage",
            "description": "<p>请搜阶段</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "secondLiquidateStage",
            "description": "<p>二级清收阶段</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "sendStatus",
            "description": "<p>发送状态</p>"
          },
          {
            "group": "Parameter",
            "type": "boolean",
            "optional": true,
            "field": "mine",
            "description": "<p>只查自己</p>"
          },
          {
            "group": "Parameter",
            "type": "boolean",
            "optional": true,
            "field": "team",
            "description": "<p>只查团队</p>"
          },
          {
            "group": "Parameter",
            "type": "boolean",
            "optional": true,
            "field": "collection",
            "description": "<p>催收ｔａｂ</p>"
          },
          {
            "group": "Parameter",
            "type": "boolean",
            "optional": false,
            "field": "isPaging",
            "description": "<p>是否分页true分页,false不分页c_pageList请求必传</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "startPageNum",
            "description": "<p>当前分页</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "pageSize",
            "description": "<p>分页大小</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": true,
            "field": "createAt",
            "description": "<p>创建时间</p>"
          }
        ]
      }
    }
  },
  {
    "type": "GET",
    "url": "http://{url}/follow_up/unread_count",
    "title": "读取未读的数量",
    "name": "unread_count",
    "group": "followUp",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": [\n{\n\"count\": 2,\n\"moment\": 111\n},\n{\n\"count\": 1,\n\"moment\": 222\n}\n]\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/FollowUpController.java",
    "groupTitle": "followUp",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/follow_up/unread_count"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://{url}/iou/add",
    "title": "新增借据",
    "name": "add",
    "group": "iou",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>新增后的数据ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/IouApidoc.java",
    "groupTitle": "iou",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/iou/add"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "id",
            "description": "<p>主键</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "iouNo",
            "description": "<p>编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "iouName",
            "description": "<p>借据名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "type",
            "description": "<p>借据类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "agency",
            "description": "<p>代理机构</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "loanTime",
            "description": "<p>放款时间</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "loanAtTime",
            "description": "<p>到款时间</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "amount",
            "description": "<p>金额</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "pactRate",
            "description": "<p>合同利率</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "outtimeMultiple",
            "description": "<p>逾期倍数</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "appropriationMultiple",
            "description": "<p>挪用倍数</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "accrualRepay",
            "description": "<p>已还利息金额</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "loanRepay",
            "description": "<p>已还贷款金额</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "levelType",
            "description": "<p>5级分类</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "outDays",
            "description": "<p>逾期天数</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "lessCorpus",
            "description": "<p>剩余本金</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "accrualArrears",
            "description": "<p>拖欠利息</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "penalty",
            "description": "<p>罚息</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "arrears",
            "description": "<p>欠款合计</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "endAt",
            "description": "<p>欠款截止日期</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "worth",
            "description": "<p>价值</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "advanceCorpus",
            "description": "<p>提前偿还本金</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateExcellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateLevel",
            "description": "<p>评级</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lenderId",
            "description": "<p>借款基础信息</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnIds",
            "description": "<p>抵押物IDs</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnNames",
            "description": "<p>抵押物名称集合</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "http://{url}/iou/delete",
    "title": "删除借据",
    "name": "delete",
    "group": "iou",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>借据ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/IouApidoc.java",
    "groupTitle": "iou",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/iou/delete"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://{url}/iou/get",
    "title": "获取借据信息",
    "name": "get",
    "group": "iou",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>借据ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "IouDTO",
            "optional": false,
            "field": "data",
            "description": "<p>借据信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "IouDTO:",
          "content": "{\n\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/IouDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/IouApidoc.java",
    "groupTitle": "iou",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/iou/get"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://{url}/iou/listAdd",
    "title": "新增借据(多条)",
    "name": "listAdd",
    "group": "iou",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "IouDTOList",
            "optional": false,
            "field": "iouDTOList",
            "description": "<p>参考新增借据</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "id",
            "description": "<p>主键</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "iouNo",
            "description": "<p>编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "iouName",
            "description": "<p>借据名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "type",
            "description": "<p>借据类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "agency",
            "description": "<p>代理机构</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "loanTime",
            "description": "<p>放款时间</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "loanAtTime",
            "description": "<p>到款时间</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "amount",
            "description": "<p>金额</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "pactRate",
            "description": "<p>合同利率</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "outtimeMultiple",
            "description": "<p>逾期倍数</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "appropriationMultiple",
            "description": "<p>挪用倍数</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "accrualRepay",
            "description": "<p>已还利息金额</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "loanRepay",
            "description": "<p>已还贷款金额</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "levelType",
            "description": "<p>5级分类</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "outDays",
            "description": "<p>逾期天数</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "lessCorpus",
            "description": "<p>剩余本金</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "accrualArrears",
            "description": "<p>拖欠利息</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "penalty",
            "description": "<p>罚息</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "arrears",
            "description": "<p>欠款合计</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "endAt",
            "description": "<p>欠款截止日期</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "worth",
            "description": "<p>价值</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "advanceCorpus",
            "description": "<p>提前偿还本金</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateExcellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateLevel",
            "description": "<p>评级</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lenderId",
            "description": "<p>借款基础信息</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnIds",
            "description": "<p>抵押物IDs</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnNames",
            "description": "<p>抵押物名称集合</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/IouApidoc.java",
    "groupTitle": "iou",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/iou/listAdd"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://{url}/iou/listIou",
    "title": "获取借款人的借据信息",
    "name": "listIou",
    "group": "iou",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>借款人ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "IouDTO",
            "optional": false,
            "field": "data",
            "description": "<p>借据信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "IouDTO:",
          "content": "{\n\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/IouDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/IouApidoc.java",
    "groupTitle": "iou",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/iou/listIou"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://{url}/iou/update",
    "title": "修改借据信息",
    "name": "update",
    "group": "iou",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>修改后的ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/IouApidoc.java",
    "groupTitle": "iou",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/iou/update"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "id",
            "description": "<p>主键</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "iouNo",
            "description": "<p>编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "iouName",
            "description": "<p>借据名称</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "type",
            "description": "<p>借据类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "agency",
            "description": "<p>代理机构</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "loanTime",
            "description": "<p>放款时间</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "loanAtTime",
            "description": "<p>到款时间</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "amount",
            "description": "<p>金额</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "pactRate",
            "description": "<p>合同利率</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "outtimeMultiple",
            "description": "<p>逾期倍数</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "appropriationMultiple",
            "description": "<p>挪用倍数</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "accrualRepay",
            "description": "<p>已还利息金额</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "loanRepay",
            "description": "<p>已还贷款金额</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "levelType",
            "description": "<p>5级分类</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "outDays",
            "description": "<p>逾期天数</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "lessCorpus",
            "description": "<p>剩余本金</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "accrualArrears",
            "description": "<p>拖欠利息</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "penalty",
            "description": "<p>罚息</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "arrears",
            "description": "<p>欠款合计</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": false,
            "field": "endAt",
            "description": "<p>欠款截止日期</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "worth",
            "description": "<p>价值</p>"
          },
          {
            "group": "Parameter",
            "type": "numbder",
            "optional": false,
            "field": "advanceCorpus",
            "description": "<p>提前偿还本金</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateExcellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateLevel",
            "description": "<p>评级</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lenderId",
            "description": "<p>借款基础信息</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnIds",
            "description": "<p>抵押物IDs</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnNames",
            "description": "<p>抵押物名称集合</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "http://{url}/lender/add",
    "title": "新增借款人",
    "name": "add",
    "group": "lender",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "LenderDTO",
            "optional": false,
            "field": "lenderDTO",
            "description": "<p>借款人基础信息</p>"
          },
          {
            "group": "Parameter",
            "type": "ContactDTO",
            "optional": false,
            "field": "contactDTOList",
            "description": "<p>联系人集合</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>增加后的数据ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "lenderDTO:",
          "content": "{\n\"id\": 1,\n\"assetId\": 1,\n\"startAt\": \"2016-07-06\",\n\"endAt\": \"2016-09-06\",\n\"accrual\": null,\n\"loan\": null,\n\"appraisal\": null,\n\"loanType\": null,\n\"loanMode\": null,\n\"loanName\": null,\n\"evaluateExcellent\": null,\n\"evaluateLevel\": null,\n\"disposeMode\": null,\n\"tags\": null,\n\"urgeType\": null,\n\"entrustBornType\": null,\n\"entrustBorn\": null,\n\"guaranteeType\": null,\n\"guaranteeMode\": null,\n\"guaranteeSource\": null,\n\"isGuaranteeConnection\": null,\n\"guarenteeEconomic\": null,\n\"isLawsuit\": null,\n\"isDecision\": null,\n\"realUrgeTime\": null,\n\"phoneUrgeTime\": null,\n\"entrustUrgeTime\": null,\n\"canContact\": null,\n\"canPay\": null,\n\"isWorth\": null,\n\"memo\": null,\n\"operatorId\": 0,\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/LenderDTO.java",
          "type": "json"
        },
        {
          "title": "ContactDTO:",
          "content": "{\n    \"id\": 917,\n    \"name\": \"张飞\",\n    \"type\": 1,\n    \"idcard\": \"452722197507190776\",\n    \"mobile\": \"13855552225\",\n    \"mode\": \"11\",\n    \"modeId\": 245,\n    \"avg\": null,\n    \"gender\": 1,\n    \"company\": \"尴尬啊啊\",\n    \"homeTel\": \"05714444222\",\n    \"officeTel\": \"05713333222\",\n    \"email\": null,\n    \"province\": \"12\",\n    \"city\": \"1202\",\n    \"district\": \"120221\",\n    \"address\": \"嘎嘎嘎\",\n    \"memo\": null,\n    \"code\": null,\n    \"otherAddress\": \"[]\"\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/ContactDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/LenderApidoc.java",
    "groupTitle": "lender",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/lender/add"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://{url}/lender/delete",
    "title": "删除借款人",
    "name": "delete",
    "group": "lender",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>借款人Id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/LenderApidoc.java",
    "groupTitle": "lender",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/lender/delete"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://{url}/lender/get",
    "title": "获取借款人信息",
    "name": "get",
    "group": "lender",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>借款人ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "LenderDTO",
            "optional": false,
            "field": "data",
            "description": "<p>联系人信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "lenderDTO:",
          "content": "{\n\"id\": 1,\n\"assetId\": 1,\n\"startAt\": \"2016-07-06\",\n\"endAt\": \"2016-09-06\",\n\"accrual\": null,\n\"loan\": null,\n\"appraisal\": null,\n\"loanType\": null,\n\"loanMode\": null,\n\"loanName\": null,\n\"evaluateExcellent\": null,\n\"evaluateLevel\": null,\n\"disposeMode\": null,\n\"tags\": null,\n\"urgeType\": null,\n\"entrustBornType\": null,\n\"entrustBorn\": null,\n\"guaranteeType\": null,\n\"guaranteeMode\": null,\n\"guaranteeSource\": null,\n\"isGuaranteeConnection\": null,\n\"guarenteeEconomic\": null,\n\"isLawsuit\": null,\n\"isDecision\": null,\n\"realUrgeTime\": null,\n\"phoneUrgeTime\": null,\n\"entrustUrgeTime\": null,\n\"canContact\": null,\n\"canPay\": null,\n\"isWorth\": null,\n\"memo\": null,\n\"operatorId\": 0,\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/LenderDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/LenderApidoc.java",
    "groupTitle": "lender",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/lender/get"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://{url}/lender/getInit",
    "title": "获取初始化数据",
    "name": "getInit",
    "group": "lender",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "SelectonDTO",
            "optional": false,
            "field": "lenderType",
            "description": "<p>借款人联系人类型</p>"
          },
          {
            "group": "Success 200",
            "type": "SelectonDTO",
            "optional": false,
            "field": "companyType",
            "description": "<p>公司类型集合</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "SelectDTO-Success-Response:",
          "content": "{\n    key:key,\n    value:\"value\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/LenderApidoc.java",
    "groupTitle": "lender",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/lender/getInit"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://{url}/lender/getLenderAll",
    "title": "获取联系人所有相关信息",
    "name": "getLenderAll",
    "group": "lender",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>借款人ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "ContactDTO",
            "optional": false,
            "field": "contactDTOs",
            "description": "<p>相关联系人信息</p>"
          },
          {
            "group": "Success 200",
            "type": "LenderDTO",
            "optional": false,
            "field": "lenderDTO",
            "description": "<p>借款人基础信息</p>"
          },
          {
            "group": "Success 200",
            "type": "IouDTO",
            "optional": false,
            "field": "iouDTOs",
            "description": "<p>借据信息</p>"
          },
          {
            "group": "Success 200",
            "type": "PawnDTO",
            "optional": false,
            "field": "pawnDTOs",
            "description": "<p>抵押物信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "ContactDTO:",
          "content": "{\n    \"id\": 917,\n    \"name\": \"张飞\",\n    \"type\": 1,\n    \"idcard\": \"452722197507190776\",\n    \"mobile\": \"13855552225\",\n    \"mode\": \"11\",\n    \"modeId\": 245,\n    \"avg\": null,\n    \"gender\": 1,\n    \"company\": \"尴尬啊啊\",\n    \"homeTel\": \"05714444222\",\n    \"officeTel\": \"05713333222\",\n    \"email\": null,\n    \"province\": \"12\",\n    \"city\": \"1202\",\n    \"district\": \"120221\",\n    \"address\": \"嘎嘎嘎\",\n    \"memo\": null,\n    \"code\": null,\n    \"otherAddress\": \"[]\"\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/ContactDTO.java",
          "type": "json"
        },
        {
          "title": "lenderDTO:",
          "content": "{\n\"id\": 1,\n\"assetId\": 1,\n\"startAt\": \"2016-07-06\",\n\"endAt\": \"2016-09-06\",\n\"accrual\": null,\n\"loan\": null,\n\"appraisal\": null,\n\"loanType\": null,\n\"loanMode\": null,\n\"loanName\": null,\n\"evaluateExcellent\": null,\n\"evaluateLevel\": null,\n\"disposeMode\": null,\n\"tags\": null,\n\"urgeType\": null,\n\"entrustBornType\": null,\n\"entrustBorn\": null,\n\"guaranteeType\": null,\n\"guaranteeMode\": null,\n\"guaranteeSource\": null,\n\"isGuaranteeConnection\": null,\n\"guarenteeEconomic\": null,\n\"isLawsuit\": null,\n\"isDecision\": null,\n\"realUrgeTime\": null,\n\"phoneUrgeTime\": null,\n\"entrustUrgeTime\": null,\n\"canContact\": null,\n\"canPay\": null,\n\"isWorth\": null,\n\"memo\": null,\n\"operatorId\": 0,\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/LenderDTO.java",
          "type": "json"
        },
        {
          "title": "IouDTO:",
          "content": "{\n\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/IouDTO.java",
          "type": "json"
        },
        {
          "title": "PawnDTO:",
          "content": "{\n    \"id\": 843,\n    \"pawnNo\": \"CO16090004\",\n    \"pawnName\": \"抵押物A\",\n    \"amount\": 22,\n    \"type\": \"1\",\n    \"evaluateExcellent\": \"1\",\n    \"evaluateLevel\": \"A\",\n    \"size\": \"0\",\n    \"province\": 14,\n    \"city\": 1403,\n    \"district\": 140302,\n    \"address\": \"嘎嘎嘎嘎嘎\",\n    \"pawnRate\": 2,\n    \"disposeStatus\": \"y\",\n    \"worth\": 23,\n    \"memo\": null,\n    \"lenderId\": 245,\n    \"iouIds\": null,\n    \"iouNames\": null,\n    \"agent\":0\n    \"urge\":0\n    \"lawyer\":0\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/PawnDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/LenderApidoc.java",
    "groupTitle": "lender",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/lender/getLenderAll"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://{url}/lender/list",
    "title": "获取借款人列表",
    "name": "list",
    "group": "lender",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "nav",
            "description": "<p>具体的导航栏项目</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>主键</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "entrustId",
            "description": "<p>委托方</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "search",
            "description": "<p>搜索(客户编号|姓名|电话|注释?)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "urgeType",
            "description": "<p>催收阶段</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "assetNo",
            "description": "<p>资产包编号</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "belong",
            "description": "<p>所属人</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "outDays",
            "description": "<p>N天以上未催收</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isOutTime",
            "description": "<p>逾期维护</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "canContact",
            "description": "<p>可联系</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isAssigned",
            "description": "<p>已分配</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isOwn",
            "description": "<p>自己录入</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isAsset",
            "description": "<p>非资产包</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "passOut",
            "description": "<p>转出</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "passIn",
            "description": "<p>转入</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isWorth",
            "description": "<p>资不抵债</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "waitAssist",
            "description": "<p>转协助的</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "assist",
            "description": "<p>正在协助</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "LenderListDTO",
            "optional": false,
            "field": "data",
            "description": "<p>借款人列表信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "lenderListDTO:",
          "content": "{\n    \"id\": 235,\n    \"name\": \"借款人\",\n    \"type\": \"借款人\",\n    \"born\": \"类型-1\",\n    \"assetName\": \"委托方资产包\",\n    \"excellent\": \"非常好\",\n    \"level\": \"B\",\n    \"dispose\": \"处置方式\",\n    \"tag\": null,\n    \"isLawsuit\": \"是\",\n    \"guaranteeType\": null,\n    \"guaranteeMode\": null,\n    \"guaranteeSource\": null,\n    \"guaranteeEconomic\": null,\n    \"guaranteeContact\": \"是\",\n    \"isWorth\": \"是\",\n    \"isLawsuit\": \"是\",\n    \"isDecision\": \"是\",\n    \"realUrgeTime\": 1,\n    \"phoneUrgeTime\": null,\n    \"entrustTime\": null,\n    \"canContact\": \"是\",\n    \"canPay\": \"是\",\n    \"memo\": null\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/LenderListDTO.java",
          "type": "json"
        },
        {
          "title": "ContactDTO:",
          "content": "{\n    \"id\": 917,\n    \"name\": \"张飞\",\n    \"type\": 1,\n    \"idcard\": \"452722197507190776\",\n    \"mobile\": \"13855552225\",\n    \"mode\": \"11\",\n    \"modeId\": 245,\n    \"avg\": null,\n    \"gender\": 1,\n    \"company\": \"尴尬啊啊\",\n    \"homeTel\": \"05714444222\",\n    \"officeTel\": \"05713333222\",\n    \"email\": null,\n    \"province\": \"12\",\n    \"city\": \"1202\",\n    \"district\": \"120221\",\n    \"address\": \"嘎嘎嘎\",\n    \"memo\": null,\n    \"code\": null,\n    \"otherAddress\": \"[]\"\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/ContactDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/LenderApidoc.java",
    "groupTitle": "lender",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/lender/list"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://{url}/lender/update",
    "title": "修改借款人",
    "name": "update",
    "group": "lender",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "LenderDTO",
            "optional": false,
            "field": "lenderDTO",
            "description": "<p>借款人基础信息</p>"
          },
          {
            "group": "Parameter",
            "type": "ContactDTO",
            "optional": false,
            "field": "contactDTOs",
            "description": "<p>联系人集合</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>修改后的数据ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "lenderDTO:",
          "content": "{\n\"id\": 1,\n\"assetId\": 1,\n\"startAt\": \"2016-07-06\",\n\"endAt\": \"2016-09-06\",\n\"accrual\": null,\n\"loan\": null,\n\"appraisal\": null,\n\"loanType\": null,\n\"loanMode\": null,\n\"loanName\": null,\n\"evaluateExcellent\": null,\n\"evaluateLevel\": null,\n\"disposeMode\": null,\n\"tags\": null,\n\"urgeType\": null,\n\"entrustBornType\": null,\n\"entrustBorn\": null,\n\"guaranteeType\": null,\n\"guaranteeMode\": null,\n\"guaranteeSource\": null,\n\"isGuaranteeConnection\": null,\n\"guarenteeEconomic\": null,\n\"isLawsuit\": null,\n\"isDecision\": null,\n\"realUrgeTime\": null,\n\"phoneUrgeTime\": null,\n\"entrustUrgeTime\": null,\n\"canContact\": null,\n\"canPay\": null,\n\"isWorth\": null,\n\"memo\": null,\n\"operatorId\": 0,\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/LenderDTO.java",
          "type": "json"
        },
        {
          "title": "ContactDTO:",
          "content": "{\n    \"id\": 917,\n    \"name\": \"张飞\",\n    \"type\": 1,\n    \"idcard\": \"452722197507190776\",\n    \"mobile\": \"13855552225\",\n    \"mode\": \"11\",\n    \"modeId\": 245,\n    \"avg\": null,\n    \"gender\": 1,\n    \"company\": \"尴尬啊啊\",\n    \"homeTel\": \"05714444222\",\n    \"officeTel\": \"05713333222\",\n    \"email\": null,\n    \"province\": \"12\",\n    \"city\": \"1202\",\n    \"district\": \"120221\",\n    \"address\": \"嘎嘎嘎\",\n    \"memo\": null,\n    \"code\": null,\n    \"otherAddress\": \"[]\"\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/ContactDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/LenderApidoc.java",
    "groupTitle": "lender",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/lender/update"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/nav/add",
    "title": "增加导航栏",
    "name": "add",
    "group": "navigation",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>新增key</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/NavigationApidoc.java",
    "groupTitle": "navigation",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/nav/add"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>主键key</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "value",
            "description": "<p>地址值path</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称value</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "pid",
            "description": "<p>父级key</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "manager",
            "description": "<p>管理员(1为可视,下同)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "governor",
            "description": "<p>管理者</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "employee",
            "description": "<p>普通员工</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "platform",
            "description": "<p>平台</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "personal",
            "description": "<p>个人</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "entrust",
            "description": "<p>委托</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "collection",
            "description": "<p>催收</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "agent",
            "description": "<p>中介</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "law",
            "description": "<p>律所</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "sort",
            "description": "<p>排序(数值越大越优先)</p>"
          }
        ]
      }
    }
  },
  {
    "type": "POST",
    "url": "http://{url}/nav/delete",
    "title": "删除导航栏",
    "name": "delete",
    "group": "navigation",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>导航栏key</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/NavigationApidoc.java",
    "groupTitle": "navigation",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/nav/delete"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://{url}/nav/get",
    "title": "获取导航栏",
    "name": "get",
    "group": "navigation",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>导航栏KEY</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "NavigationDTO",
            "optional": false,
            "field": "data",
            "description": "<p>导航栏数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "NavigationDTO:",
          "content": "{\n    \"key\": 13,\n    \"value\": \"操作日志\",\n    \"path\": \"/memo\",\n    \"icon\": \"\",\n    \"child\": true,\n    \"group\": null\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/NavigationDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/NavigationApidoc.java",
    "groupTitle": "navigation",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/nav/get"
      }
    ]
  },
  {
    "type": "GET",
    "url": "http://{url}/nav/getTop",
    "title": "获取初始导航栏",
    "name": "getTop",
    "group": "navigation",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "NavigationDTO",
            "optional": false,
            "field": "data",
            "description": "<p>导航栏数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "NavigationDTO:",
          "content": "{\n    \"key\": 13,\n    \"value\": \"操作日志\",\n    \"path\": \"/memo\",\n    \"icon\": \"\",\n    \"child\": true,\n    \"group\": null\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/NavigationDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/NavigationApidoc.java",
    "groupTitle": "navigation",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/nav/getTop"
      }
    ]
  },
  {
    "type": "GET",
    "url": "http://{url}/nav/listById",
    "title": "根据导航栏key获取子导航栏",
    "name": "listById",
    "group": "navigation",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>导航栏key</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "NavigationDTO",
            "optional": false,
            "field": "data",
            "description": "<p>导航栏数据</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "NavigationDTO:",
          "content": "{\n    \"key\": 13,\n    \"value\": \"操作日志\",\n    \"path\": \"/memo\",\n    \"icon\": \"\",\n    \"child\": true,\n    \"group\": null\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/NavigationDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/NavigationApidoc.java",
    "groupTitle": "navigation",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/nav/listById"
      }
    ]
  },
  {
    "type": "POST",
    "url": "http://{url}/nav/update",
    "title": "修改导航栏",
    "name": "update",
    "group": "navigation",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>修改后的数据ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/NavigationApidoc.java",
    "groupTitle": "navigation",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/nav/update"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>主键key</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "value",
            "description": "<p>地址值path</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>名称value</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "pid",
            "description": "<p>父级key</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "manager",
            "description": "<p>管理员(1为可视,下同)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "governor",
            "description": "<p>管理者</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "employee",
            "description": "<p>普通员工</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "platform",
            "description": "<p>平台</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "personal",
            "description": "<p>个人</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "entrust",
            "description": "<p>委托</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "collection",
            "description": "<p>催收</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "agent",
            "description": "<p>中介</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "law",
            "description": "<p>律所</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "sort",
            "description": "<p>排序(数值越大越优先)</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "operLog/pageList",
    "title": "操作记录",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "page",
            "description": "<p>分页数</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "pageCount",
            "description": "<p>显示数目</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "time",
            "description": "<p>操作时间</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "userId",
            "description": "<p>用户id</p>"
          }
        ]
      }
    },
    "description": "<p>查询操作记录信息</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/operLog/pageList"
      }
    ],
    "group": "operLog",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/OperLogController.java",
    "groupTitle": "operLog",
    "name": "PostOperlogPagelist"
  },
  {
    "type": "post",
    "url": "operLog/update",
    "title": "修改备注",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "id",
            "description": "<p>操作记录id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          }
        ]
      }
    },
    "description": "<p>修改备注</p>",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/operLog/update"
      }
    ],
    "group": "operLog",
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/OperLogController.java",
    "groupTitle": "operLog",
    "name": "PostOperlogUpdate"
  },
  {
    "type": "POST",
    "url": "/api/company/addOrganization",
    "title": "新增组织(部门|团队)",
    "name": "addOrganization",
    "group": "organization",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>新增的ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "organization",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/addOrganization"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "type",
            "description": "<p>类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>名称</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "userId",
            "description": "<p>负责人ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "companyId",
            "description": "<p>公司ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "pid",
            "description": "<p>上级ID</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "/api/company/deleteOrganization",
    "title": "删除组织(部门|团队)",
    "name": "deleteOrganization",
    "group": "organization",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>组织ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "organization",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/deleteOrganization"
      }
    ]
  },
  {
    "type": "get",
    "url": "/api/company/getOrganization",
    "title": "获取组织(部门|团队)",
    "name": "getOrganization",
    "group": "organization",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "type",
            "description": "<p>组织类型</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>组织ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "OrganizationDTO",
            "optional": false,
            "field": "data",
            "description": "<p>返回组织架构</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Organization:",
          "content": "{\n\"id\": 4,\n\"name\": \"管理层\",\n\"user\": null,\n\"userId\": null\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/OrganizationDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "organization",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/getOrganization"
      }
    ]
  },
  {
    "type": "GET",
    "url": "/api/company/listCompany",
    "title": "查看特定类型的公司",
    "name": "listCompany",
    "group": "organization",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "type",
            "description": "<p>公司类型(1平台2委托31催收32律所33中介)</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "CompanyDTO",
            "optional": false,
            "field": "data",
            "description": "<p>公司信息</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "organization",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/listCompany"
      }
    ]
  },
  {
    "type": "GET",
    "url": "/api/company/listOrganization",
    "title": "查看组织列表(部门|团队)",
    "name": "listOrganization",
    "group": "organization",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "companyId",
            "description": "<p>公司Id</p>"
          },
          {
            "group": "Parameter",
            "type": "type",
            "optional": false,
            "field": "type",
            "description": "<p>组织类型(apartment部门|team团队)</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "OrganizationDTO",
            "optional": false,
            "field": "data",
            "description": "<p>组织架构</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Organization:",
          "content": "{\n\"id\": 4,\n\"name\": \"管理层\",\n\"user\": null,\n\"userId\": null\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/company/OrganizationDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "organization",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/listOrganization"
      }
    ]
  },
  {
    "type": "POST",
    "url": "/api/company/updateOrganization",
    "title": "修改组织(部门|团队)",
    "name": "updateOrganization",
    "group": "organization",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>修改后的ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/CompanyApidoc.java",
    "groupTitle": "organization",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080//api/company/updateOrganization"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "type",
            "description": "<p>类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>名称</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "userId",
            "description": "<p>负责人ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "companyId",
            "description": "<p>公司ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "pid",
            "description": "<p>上级ID</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "remark",
            "description": "<p>备注</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "part/getDetail",
    "title": "详情信息",
    "name": "part_getDetail",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/part/getDetail"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "companyId",
            "description": "<p>公司id</p>"
          }
        ]
      }
    },
    "group": "part",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"result\": \"yes\",\n\"list\": [\n{\n\"companyName\": \"风腾律所\",//公司名称\n\"companyId\": 419,//公司id\n\"companyContent\": \"说走就走，走一个\",//简介\n\"companyJoinTime\": \"2016-10-21\",//参与时间\n\"joinPeopleNum\": 0,//参与人数\n\"comapanyTotal\": 36,//公司总人数（规模）\n\"companyAddress\": \"北京市市辖区东城区\",//地址\n\"rate\": \"0\",//处置效率\n\"companyImg\": null //公司logo\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/ParticipationController.java",
    "groupTitle": "part"
  },
  {
    "type": "post",
    "url": "part/getList",
    "title": "操作接口",
    "name": "part_getList",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/part/getList"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型</p>"
          }
        ]
      }
    },
    "group": "part",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"result\": \"yes\",\n\"list\": [\n{\n\"companyName\": \"风腾律所\",//公司名称\n\"companyId\": 419,//公司id\n\"companyContent\": \"说走就走，走一个\",//简介\n\"companyJoinTime\": \"2016-10-21\",//参与时间\n\"joinPeopleNum\": 0,//参与人数\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/ParticipationController.java",
    "groupTitle": "part"
  },
  {
    "type": "post",
    "url": "http://{url}/pawn/add",
    "title": "增加抵押物信息",
    "name": "add",
    "group": "pawn",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>增加后的数据ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/PawnApidoc.java",
    "groupTitle": "pawn",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/pawn/add"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>主键</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lenderId",
            "description": "<p>借款人ID</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnNo",
            "description": "<p>抵押物编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnName",
            "description": "<p>抵押物名称</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "amount",
            "description": "<p>贷款金额</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "type",
            "description": "<p>抵押物类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateExcellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateLevel",
            "description": "<p>评级</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "size",
            "description": "<p>规模大小</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "province",
            "description": "<p>省份</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "city",
            "description": "<p>城市</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "district",
            "description": "<p>区域</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "address",
            "description": "<p>详细地址</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "pawnRate",
            "description": "<p>抵押率</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "disposeStatus",
            "description": "<p>处置状态</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "worth",
            "description": "<p>价值</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "iouIds",
            "description": "<p>借据Id集合</p>"
          }
        ]
      }
    }
  },
  {
    "type": "get",
    "url": "http://{url}/pawn/delete",
    "title": "删除抵押物",
    "name": "delete",
    "group": "pawn",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>抵押物ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/PawnApidoc.java",
    "groupTitle": "pawn",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/pawn/delete"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://{url}/pawn/get",
    "title": "获取抵押物信息",
    "name": "get",
    "group": "pawn",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>抵押物ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "PawnDTO",
            "optional": false,
            "field": "data",
            "description": "<p>抵押物信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "PawnDTO:",
          "content": "{\n    \"id\": 843,\n    \"pawnNo\": \"CO16090004\",\n    \"pawnName\": \"抵押物A\",\n    \"amount\": 22,\n    \"type\": \"1\",\n    \"evaluateExcellent\": \"1\",\n    \"evaluateLevel\": \"A\",\n    \"size\": \"0\",\n    \"province\": 14,\n    \"city\": 1403,\n    \"district\": 140302,\n    \"address\": \"嘎嘎嘎嘎嘎\",\n    \"pawnRate\": 2,\n    \"disposeStatus\": \"y\",\n    \"worth\": 23,\n    \"memo\": null,\n    \"lenderId\": 245,\n    \"iouIds\": null,\n    \"iouNames\": null,\n    \"agent\":0\n    \"urge\":0\n    \"lawyer\":0\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/PawnDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/PawnApidoc.java",
    "groupTitle": "pawn",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/pawn/get"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://{url}/pawn/listAdd",
    "title": "增加抵押物信息(多条)",
    "name": "listAdd",
    "group": "pawn",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "objectList",
            "optional": false,
            "field": "pawnDTOList",
            "description": "<p>参考增加抵押物信息</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/PawnApidoc.java",
    "groupTitle": "pawn",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/pawn/listAdd"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://{url}/pawn/listPawn",
    "title": "获取借款人的抵押物信息",
    "name": "listPawn",
    "group": "pawn",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>借款人ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "PawnDTO",
            "optional": false,
            "field": "data",
            "description": "<p>抵押物信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "PawnDTO:",
          "content": "{\n    \"id\": 843,\n    \"pawnNo\": \"CO16090004\",\n    \"pawnName\": \"抵押物A\",\n    \"amount\": 22,\n    \"type\": \"1\",\n    \"evaluateExcellent\": \"1\",\n    \"evaluateLevel\": \"A\",\n    \"size\": \"0\",\n    \"province\": 14,\n    \"city\": 1403,\n    \"district\": 140302,\n    \"address\": \"嘎嘎嘎嘎嘎\",\n    \"pawnRate\": 2,\n    \"disposeStatus\": \"y\",\n    \"worth\": 23,\n    \"memo\": null,\n    \"lenderId\": 245,\n    \"iouIds\": null,\n    \"iouNames\": null,\n    \"agent\":0\n    \"urge\":0\n    \"lawyer\":0\n}\ngit 地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/asset/PawnDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/PawnApidoc.java",
    "groupTitle": "pawn",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/pawn/listPawn"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://{url}/pawn/update",
    "title": "修改抵押物信息",
    "name": "update",
    "group": "pawn",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>修改后的数据ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/PawnApidoc.java",
    "groupTitle": "pawn",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/pawn/update"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>主键</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lenderId",
            "description": "<p>借款人ID</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnNo",
            "description": "<p>抵押物编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "pawnName",
            "description": "<p>抵押物名称</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "amount",
            "description": "<p>贷款金额</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "type",
            "description": "<p>抵押物类型</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateExcellent",
            "description": "<p>评优</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "evaluateLevel",
            "description": "<p>评级</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "size",
            "description": "<p>规模大小</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "province",
            "description": "<p>省份</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "city",
            "description": "<p>城市</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "district",
            "description": "<p>区域</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "address",
            "description": "<p>详细地址</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "pawnRate",
            "description": "<p>抵押率</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "disposeStatus",
            "description": "<p>处置状态</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "worth",
            "description": "<p>价值</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>备注</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "iouIds",
            "description": "<p>借据Id集合</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "http://{url}/source/add",
    "title": "增加资源",
    "name": "add",
    "group": "source",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "object",
            "optional": false,
            "field": "sourceInfoDTO",
            "description": ""
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>新增的ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/SourceApidoc.java",
    "groupTitle": "source",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/source/add"
      }
    ]
  },
  {
    "type": "post",
    "url": "http://{url}/source/addNavigation",
    "title": "增加分类",
    "name": "addNavigation",
    "group": "source",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>新增后的数据ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>数据id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "name",
            "description": "<p>导航名称</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "pid",
            "description": "<p>上层目录ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lenderId",
            "description": "<p>默认(0),传值时表示特殊分类,该借款人独有</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>// 实勘1|证件合同0(默认) 参考git地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_orm/src/main/java/com/dqys/business/orm/pojo/common/SourceNavigation.java</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/SourceApidoc.java",
    "groupTitle": "source",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/source/addNavigation"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://{url}/source/deleteNavigation",
    "title": "删除分类",
    "name": "deleteNavigation",
    "group": "source",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "id",
            "description": "<p>分类ID</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/SourceApidoc.java",
    "groupTitle": "source",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/source/deleteNavigation"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://{url}/source/get",
    "title": "获取资源信息",
    "name": "get",
    "group": "source",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "lenderId",
            "description": "<p>借款人id（用于资产包或借款人的资料实堪）</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "estatesId",
            "description": "<p>资产源id（用于资产源的资料实堪）</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "navId",
            "description": "<p>分类ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "SourceInfoDTO",
            "optional": false,
            "field": "data",
            "description": "<p>资源信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "SourceDTO:",
          "content": "{\n    id:1,\n    navId:1,\n    lenderId:1,\n    code:\"asdasdasd\",\n    userIds:\"1,2,3\",\n    isshow:1,\n    watermark:1,\n    open:1,\n    memo:null,\n    sourceDTOList:[]\n}\ngit地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/common/SourceInfoDTO.java",
          "type": "json"
        },
        {
          "title": "SourceDTO:",
          "content": "{\n    id:1,\n    sourceId:1,\n    fileName:\"/asd/asd/a.png\",\n    name:null,\n    memo:null\n}\ngit地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/common/SourceDTO.java",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/SourceApidoc.java",
    "groupTitle": "source",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/source/get"
      }
    ]
  },
  {
    "type": "get",
    "url": "http://{url}/source/listNavigation",
    "title": "分类列表",
    "name": "listNavigation",
    "group": "source",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "lenderId",
            "description": "<p>借款人id（用于资产包或借款人的资料实堪）</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "estatesId",
            "description": "<p>资产源id（用于资产源的资料实堪）</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "type",
            "description": "<p>实勘1|证件合同0(默认)</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "SelectonDTOList",
            "optional": false,
            "field": "data",
            "description": "<p>分类详细信息</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "SelectDTO-Success-Response:",
          "content": "{\n    key:key,\n    value:\"value\",\n    children:[SelectDTOList]\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/SourceApidoc.java",
    "groupTitle": "source",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/source/listNavigation"
      }
    ]
  },
  {
    "type": "post",
    "url": "source/getSourceType",
    "title": "获取资料实堪分类操作的是否有权限",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "navId",
            "description": "<p>分类id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectId",
            "description": "<p>对象id</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "objectType",
            "description": "<p>对象类型</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/source/getSourceType"
      }
    ],
    "group": "source",
    "name": "source_getSourceType",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": [\n{\n\"number\": 145,\n\"name\": \"添加标签\",\n\"url\": \"\"\n},\n{\n\"number\": 146,\n\"name\": \"重命名分类\",\n\"url\": \"\"\n},\n{\n\"number\": 147,\n\"name\": \"删除分类\",\n\"url\": \"\"\n}\n]\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/SourceController.java",
    "groupTitle": "source"
  },
  {
    "type": "post",
    "url": "http://{url}/source/update",
    "title": "修改资源信息",
    "name": "update",
    "group": "source",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "number",
            "optional": false,
            "field": "data",
            "description": "<p>修改后的数据id</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/apiDoc/SourceApidoc.java",
    "groupTitle": "source",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/source/update"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>数据ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "navId",
            "description": "<p>分类ID</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "lenderId",
            "description": "<p>借款人Id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "code",
            "description": "<p>文件编号</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "userIds",
            "description": "<p>可视人员ID的集合，“,”隔开</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "isshow",
            "description": "<p>是否展示外网(1是0否)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "watermark",
            "description": "<p>水印(1有0没有)</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": false,
            "field": "open",
            "description": "<p>公开(1公开0不公开)</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "memo",
            "description": "<p>详情信息</p>"
          },
          {
            "group": "Parameter",
            "type": "SourceDTO",
            "optional": false,
            "field": "sourceDTOList",
            "description": "<p>保存的文件信息 git地址: http://114.215.239.181:6080/qs_plat/java/blob/master/qs_business_service/src/main/java/com/dqys/business/service/dto/common/SourceInfoDTO.java</p>"
          }
        ]
      }
    }
  },
  {
    "type": "post",
    "url": "synLender/pawnList",
    "title": "借款人信息并关联下抵押物和借据信息",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "lenderId",
            "description": "<p>借款人id</p>"
          }
        ]
      }
    },
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/synLender/pawnList"
      }
    ],
    "group": "synLender",
    "name": "synLender_pawnList",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"result\": \"yes\",\n\"iouList\": [\n{\n\"id\": 528,\n\"version\": 4,\n\"stateflag\": 0,\n\"createAt\": \"2016-09-13\",\n\"updateAt\": \"2016-09-21\",\n\"remark\": null,//标签说明\n\"iouNo\": \"IO1609139996\",//借据编号\n\"name\": \"借据01\",//借据自定义名称\n\"lenderId\": 190,//借款人ID\n\"type\": \"1\",//品种\n\"agency\": \"某某经办机构\",//代理机构\n\"iouCode\": \"B4532S\",//原始借据号\n\"loanTime\": \"2016-09-14\",//放款时间\n\"loanAttime\": \"2016-09-28\",//到款日期\n\"amount\": 20100,//放款金额\n\"pactRate\": 3,//合同利率\n\"outtimeMultiple\": 12,//逾期利率倍数\n\"appropriationMultiple\": \"1\",//挪用利率倍数\n\"accrualRepay\": 4300,//已还利息金额\n\"loanRepay\": 1200,//已还贷款金额\n\"levelType\": \"1\",//5级分类'\n\"outDays\": 120,//逾期天数\n\"lessCorpus\": 1555,//剩余本金\n\"accrualArrears\": 8500,//拖欠利息\n\"penalty\": 0,//罚息\n\"arrears\": 120000,//欠款合计\n\"endAt\": \"2016-09-21\",//欠款截止日期\n\"worth\": 20000,//抵押物银行估值\n\"advanceCorpus\": 30000,//提前偿还本金\n\"evaluateExcellent\": \"great\",//评优\n\"evaluateLevel\": \"A\",//评级\n\"memo\": \"备注一下借据\",//备注\n\"repayStatus\": 0,//还款状态（0已经还完1未还完）\n\"onCollection\": 1,//是否可以催收:0可以1不能\n\"onLawyer\": 1,//是否可以进行司法处置:0可以1不能\n\"onAgent\": 1 //是否可以中介处置:0可以1不能\n}\n],\n\"lenderSynDTO\": {\n\"avg\": null,//头像地址\n\"name\": \"张三\",// 姓名\n\"gender\": \"1\",//性别(1-男;0-女)\n\"type\": 1,//借款类型\n\"address\": \"张三在北京的住宅地址\",//详细地址\n\"lenderNo\": \"JKR7758\",//借款人编号\n\"tags\": null,//标签\n\"accrual\": \"116500.0\",//总利息\n\"loan\": \"12222.0\",// 总贷款\n\"appraisal\": \"40000.0\",//总评估\n\"out_days\": null,//逾期天数（计算？）\n\"loanType\": \"2\",//贷款类型\n\"loanMode\": \"1\",//贷款方式\n\"loanName\": \"贷款机构名称哦哦哦\",//贷款名称\n\"realUrgeTime\": 6,//实地催收次数\n\"phoneUrgeTime\": 23,//电话催收次数\n\"entrustUrgeTime\": 2,//委托催收次数\n\"followUpDate\": null,//最后跟进时间\n\"enteringDate\": \"2016-09-13\",//录入时间\n\"belongId\": null,//所属人ID\n\"belongName\": null,// 所属人姓名\n\"startAt\": \"2016-09-14\",//委托开始时间\n\"endAt\": \"2016-10-30\",//委托结束时间\n\"surplusDay\": \"-17\",//剩余委托催收天数\n\"source\": null,//来源\n\"assetNo\": \"QS1609132853\" //资产包编号\n},\n\"pawnList\": [\n{\n\"id\": 624,\n\"version\": 0,\n\"stateflag\": 0,\n\"createAt\": \"2016-09-13\",\n\"updateAt\": \"2016-09-13\",\n\"remark\": null,//标签说明'\n\"lenderId\": 190,//借款人ID\n\"pawnNo\": \"CO1609133220\",//抵押物编号\n\"name\": \"抵押物A\",//抵押物自定义名称\n\"amount\": 52200,//贷款金额\n\"type\": \"1\",//抵押物类型\n\"evaluateExcellent\": \"great\",//评优\n\"evaluateLevel\": \"A\",//评级\n\"size\": \"122\",//规模大小\n\"province\": 12,//省份\n\"city\": 1202,//城市\n\"district\": 120221,//区域\n\"address\": \"zhanljlgkggga顶顶顶\",//地址\n\"pawnRate\": 12,// 抵押率\n\"disposeStatus\": \"y\",//处置状态\n\"worth\": 40000,//价值\n\"memo\": null,//备注\n\"onCollection\": 0,//是否可以催收:0可以1不能\n\"onLawyer\": 0,//是否可以司法处置:0可以1不能\n\"onAgent\": 0 //是否可以中介处置:0可以,1不行\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/SynLenderController.java",
    "groupTitle": "synLender"
  },
  {
    "type": "GET",
    "url": "http://{url}/sys_notice/update",
    "title": "查询更进信息,并去除对应阶段的未读数据",
    "name": "add",
    "group": "sys_notice",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"id\": null,\n\"version\": null,\n\"stateflag\": null,\n\"createAt\": null,\n\"updateAt\": null,\n\"remark\": null,\n\"title\": \"11122266\",\n\"content\": \"11112266\",\n\"type\": 1,\n\"picname\": \"33332266\",\n\"userId\": 1\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/SysNoticeController.java",
    "groupTitle": "sys_notice",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/sys_notice/update"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "title",
            "description": "<p>标题</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "content",
            "description": "<p>内容</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "type",
            "description": "<p>通知类型:0系统消息</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "picname",
            "description": "<p>图片名称</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "introduce",
            "description": "<p>发布状态:0未发布,1已发布</p>"
          }
        ]
      }
    }
  },
  {
    "type": "GET",
    "url": "http://{url}/sys_notice/add",
    "title": "查询更进信息,并去除对应阶段的未读数据",
    "name": "add",
    "group": "sys_notice",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"id\": null,\n\"version\": null,\n\"stateflag\": null,\n\"createAt\": null,\n\"updateAt\": null,\n\"remark\": null,\n\"title\": \"11122266\",\n\"content\": \"11112266\",\n\"type\": 1,\n\"picname\": \"33332266\",\n\"userId\": 1\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/SysNoticeController.java",
    "groupTitle": "sys_notice",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/sys_notice/add"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "title",
            "description": "<p>标题</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": false,
            "field": "content",
            "description": "<p>内容</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "type",
            "description": "<p>通知类型:0系统消息</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "picname",
            "description": "<p>图片名称</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "introduce",
            "description": "<p>发布状态:0未发布,1已发布</p>"
          }
        ]
      }
    }
  },
  {
    "type": "GET",
    "url": "http://{url}/sys_notice/c_pageList",
    "title": "分页查询系统消息",
    "name": "c_pageList",
    "group": "sys_notice",
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"total\": 2,\n\"data\": [\n{\n\"id\": 3,\n\"version\": null,\n\"stateflag\": null,\n\"createAt\": null,\n\"updateAt\": null,\n\"remark\": null,\n\"title\": \"111\",\n\"content\": \"1111\",\n\"type\": 0,\n\"picname\": \"3333\",\n\"userId\": 1\n},\n{\n\"id\": 4,\n\"version\": null,\n\"stateflag\": null,\n\"createAt\": null,\n\"updateAt\": null,\n\"remark\": null,\n\"title\": \"111222\",\n\"content\": \"111122\",\n\"type\": 0,\n\"picname\": \"333322\",\n\"userId\": 1\n}\n]\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/SysNoticeController.java",
    "groupTitle": "sys_notice",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/sys_notice/c_pageList"
      }
    ],
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>id</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "title",
            "description": "<p>标题</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "type",
            "description": "<p>通知类型:0系统通知</p>"
          },
          {
            "group": "Parameter",
            "type": "string",
            "optional": true,
            "field": "content",
            "description": "<p>内容</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "startPageNum",
            "description": "<p>当前分页</p>"
          },
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "pageSize",
            "description": "<p>分页大小</p>"
          },
          {
            "group": "Parameter",
            "type": "date",
            "optional": true,
            "field": "createAt",
            "description": "<p>创建时间</p>"
          }
        ]
      }
    }
  },
  {
    "type": "DELETE",
    "url": "http://{url}/sys_notice/del",
    "title": "查询更进信息,并去除对应阶段的未读数据",
    "name": "del",
    "group": "sys_notice",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "number",
            "optional": true,
            "field": "id",
            "description": "<p>id</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Data-Response:",
          "content": "{\n\"code\": 2000,\n\"msg\": \"成功\",\n\"data\": {\n\"id\": null,\n\"version\": null,\n\"stateflag\": null,\n\"createAt\": null,\n\"updateAt\": null,\n\"remark\": null,\n\"title\": \"11122266\",\n\"content\": \"11112266\",\n\"type\": 1,\n\"picname\": \"33332266\",\n\"userId\": 1\n}\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./qs_business/src/main/java/com/dqys/business/controller/SysNoticeController.java",
    "groupTitle": "sys_notice",
    "sampleRequest": [
      {
        "url": "http://192.168.1.48:8080/http://{url}/sys_notice/del"
      }
    ]
  }
] });
