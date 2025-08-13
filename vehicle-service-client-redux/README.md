# Redux Vehicle Service Client

This is a standalone module that doesn't build or publish with gradle.

This project uses the Redux Toolkit OpenApi generator:
https://redux-toolkit.js.org/rtk-query/usage/code-generation.
It also uses GitHub package management.

## Client Generation

`/src/api/endpoints/vehicleApi.ts` is a generated file. It's **manually** generated, and should be regenerated
whenever the OpenAPI spec changes.
```bash
   # required for generation (only need to do this once)
   npm install -g @rtk-incubator/rtk-query-codegen-openapi
   # generate code to /src/api/endpoints/vehiclesApi.ts
   npx @rtk-query/codegen-openapi openapi-config.ts
```

In the future, I will auto generate this file.

## Github package management

The `package.json` is configured to publish to GitHub's package repository.

* Requirements
    * Node: v18.16.x
    * Configure npm to point to the GitHub registry:
      https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-npm-registry
        * If you get stuck, here's a great video that should help: https://www.youtube.com/watch?v=2-77KhGWlRg&t=511s

To build & publish, increment the version in `package.json` and run the following:

```bash
   # remove the dist output folder, build, and publish
   rm -rf dist 
   npm run build
   npm publish
```

# Setup & Run

```bash
   # from this repo, install all packages & start the app
   npm i
   npm start
```
