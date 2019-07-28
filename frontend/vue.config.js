// vue.config.js
module.exports = {
	// TODO / 이현규 / 뷰 프록시 서버 설정 관련 내용 찾아보기
	devServer: {
		proxy: {
			'/api': {
				target: 'http://localhost:8000',
				changeOrigin: true
			}
		}
	},
	// Change build paths to make them Maven compatible
	// see https://cli.vuejs.org/config/
	outputDir: 'target/dist',
	assetsDir: 'static'
};
