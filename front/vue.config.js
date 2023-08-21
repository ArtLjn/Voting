const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  devServer:{
    port:8888,
    proxy:{
      '/api':{
        target:"http://localhost:8089/api",
        ws: true,
        changeOrigin: true, //是否跨域
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
})
