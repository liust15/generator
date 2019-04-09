module.exports = {
    // 基本路径
    baseUrl: './',
    // 生产环境是否生成 sourceMap 文件
    productionSourceMap: false,
    // 服务器端口号
    devServer: {
        port: 19001,
       /* proxy: {
            '/': {
                target: 'http://localhost:8080',
                changeOrigin: true,
                pathRewrite: {
                    '/': ''
                }
            }
        }*/
    },


}
