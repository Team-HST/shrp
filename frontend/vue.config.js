module.exports = {
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
