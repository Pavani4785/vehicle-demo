import type { ConfigFile } from '@rtk-query/codegen-openapi'

const config: ConfigFile = {
  schemaFile: '../build/mergedApi.yaml',
  apiFile: './src/services/api/emptySplitApi.ts',
  apiImport: 'emptySplitApi',
  outputFile: './src/services/api/endpoints/vehiclesApi.ts',
  exportName: 'vehiclesApi',
  hooks: true,
  endpointOverrides: [
    {
      pattern: 'getVehiclesByVins',
      type: 'query',
    },
  ],
}

export default config
