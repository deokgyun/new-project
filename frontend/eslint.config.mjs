import globals from 'globals'
import pluginJs from '@eslint/js'
import tseslint from 'typescript-eslint'
import { FlatCompat } from '@eslint/eslintrc'
import prettierPlugin from 'eslint-plugin-prettier/recommended'
import tailwindConfig from 'eslint-plugin-tailwindcss'

const compat = new FlatCompat({
    baseDirectory: import.meta.dirname,
    recommendedConfig: pluginJs.configs.recommended,
})

/** @type {import('eslint').Linter.Config[]} */
export default [
    { files: ['**/*.{js,mjs,cjs,ts,tsx,jsx}'] },
    { languageOptions: { globals: globals.browser } },
    ...tseslint.configs.recommended,
    ...tailwindConfig.configs['flat/recommended'],
    ...compat.config({
        extends: ['next', 'next/core-web-vitals'],
    }),

    prettierPlugin,

    {
        rules: {
            'prettier/prettier': [
                'error',
                {
                    endOfLine: 'auto',
                },
            ],
        },
    },
]
