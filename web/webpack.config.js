const path = require('path');

module.exports = {
    entry: path.resolve(__dirname, 'src'),
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'src/main/webapp'),
        library: 'app'
    },

    resolve: {
        extensions: ['.ts', '.tsx', '.js', '.json']
    },

    module: {
        rules: [{test: /\.(ts|js)x?$/, loader: 'babel-loader', exclude: /node_modules/}],
    }
};
