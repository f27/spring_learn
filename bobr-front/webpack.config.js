const path = require('path');
const CopyPlugin = require('copy-webpack-plugin');
const webpack = require('webpack');
const dotenv = require('dotenv').config({path: `./.env.${process.env.REACT_APP_ENV}`});

const env = dotenv.parsed;

const envKeys = Object.keys(env).reduce((prev, next) => {
    prev[`process.env.${next}`] = JSON.stringify(env[next]);
    return prev;
}, {});

module.exports = {
    entry: './src/index.jsx',
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'build'),
        clean: true,
        publicPath: "/",
    },
    optimization: {
        minimize: true,
    },
    performance: {
        maxEntrypointSize: 512000,
        maxAssetSize: 512000
    },
    devtool: false,
    plugins: [
        new webpack.DefinePlugin(envKeys),
        new CopyPlugin({
            patterns: [{from: 'public'}],
        }),
    ],
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /(node_modules)/,
                use: ['babel-loader']
            },
            {
                test: /\.css$/i,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.(woff|woff2|eot|ttf|otf)$/i,
                type: 'asset/resource',
                generator: {
                    filename: 'fonts/[name][ext]'
                },
            },
        ]
    },
    resolve: {
        extensions: ['.js', '.jsx']
    },
    devServer: {
        static: {
            directory: path.join(__dirname, "build"),
        },
        historyApiFallback: true,
        port: 3000,
    },
}
