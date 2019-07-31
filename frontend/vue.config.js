module.exports = {
	transpileDependencies:[/node_modules[/\\\\]vuetify[/\\\\]/], // ie 지원 babel-polyfill설정
	devServer: {
		proxy: {
			'/api': {
				target: 'http://localhost:8000',
				changeOrigin: true,
				pathRewrite: {
					'^/api' : ''
				}
			}
		}
	},
	outputDir: 'target/dist',
	assetsDir: 'static'
};
