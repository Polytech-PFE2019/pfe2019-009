module.exports = {
    "presets": [
        [
            "@babel/preset-env",
            {
                "useBuiltIns": "usage",
                "corejs": 3,
                "targets": {
                    "esmodules": true,
                    "node": "current"
                }
            }
        ]
    ],
    "plugins": [
        "@babel/plugin-transform-modules-commonjs",
        "@babel/plugin-syntax-dynamic-import"
    ]
};