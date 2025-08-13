import { emptySplitApi as api } from "../emptySplitApi";
const injectedRtkApi = api.injectEndpoints({
  endpoints: (build) => ({
    getVehicleByVin: build.query<
      GetVehicleByVinApiResponse,
      GetVehicleByVinApiArg
    >({
      query: (queryArg) => ({
        url: `/v1/vehicles`,
        headers: {
          vin: queryArg.vin,
        },
      }),
    }),
    getVehiclesByVins: build.query<
      GetVehiclesByVinsApiResponse,
      GetVehiclesByVinsApiArg
    >({
      query: (queryArg) => ({
        url: `/v1/vehicles`,
        method: "POST",
        body: queryArg.body,
      }),
    }),
    getDcmByVin: build.query<GetDcmByVinApiResponse, GetDcmByVinApiArg>({
      query: (queryArg) => ({
        url: `/v1/dcm`,
        headers: {
          vin: queryArg.vin,
        },
      }),
    }),
    getDcmByDcmId: build.query<GetDcmByDcmIdApiResponse, GetDcmByDcmIdApiArg>({
      query: (queryArg) => ({ url: `/v1/dcm/${queryArg.dcmId}` }),
    }),
    getDcmSoftwareByVin: build.query<
      GetDcmSoftwareByVinApiResponse,
      GetDcmSoftwareByVinApiArg
    >({
      query: (queryArg) => ({
        url: `/v1/dcmSoftware`,
        headers: {
          vin: queryArg.vin,
        },
      }),
    }),
    getDcmSoftwareByDcmSoftware: build.query<
      GetDcmSoftwareByDcmSoftwareApiResponse,
      GetDcmSoftwareByDcmSoftwareApiArg
    >({
      query: (queryArg) => ({
        url: `/v1/dcmSoftware/${queryArg.dcmSoftwareId}`,
      }),
    }),
    getModelByVin: build.query<GetModelByVinApiResponse, GetModelByVinApiArg>({
      query: (queryArg) => ({
        url: `/v1/models`,
        headers: {
          vin: queryArg.vin,
        },
      }),
    }),
    getModelByModelKey: build.query<
      GetModelByModelKeyApiResponse,
      GetModelByModelKeyApiArg
    >({
      query: (queryArg) => ({ url: `/v1/models/${queryArg.modelKey}` }),
    }),
    listModelCapabilities: build.query<
      ListModelCapabilitiesApiResponse,
      ListModelCapabilitiesApiArg
    >({
      query: (queryArg) => ({
        url: `/v1/models/${queryArg.modelKey}/capabilities`,
      }),
    }),
    getCapabilityCodesByModelKey: build.query<
      GetCapabilityCodesByModelKeyApiResponse,
      GetCapabilityCodesByModelKeyApiArg
    >({
      query: (queryArg) => ({
        url: `/v1/models/${queryArg.modelKey}/capabilityCodes`,
        params: {
          includeInactive: queryArg.includeInactive,
        },
      }),
    }),
    getCapability: build.query<GetCapabilityApiResponse, GetCapabilityApiArg>({
      query: (queryArg) => ({
        url: `/v1/capabilities/${queryArg.capabilityCode}`,
      }),
    }),
    listCapabilities: build.query<
      ListCapabilitiesApiResponse,
      ListCapabilitiesApiArg
    >({
      query: () => ({ url: `/v1/capabilities` }),
    }),
    getDcmGenerationByVin: build.query<
      GetDcmGenerationByVinApiResponse,
      GetDcmGenerationByVinApiArg
    >({
      query: (queryArg) => ({
        url: `/v1/dcmGeneration`,
        headers: {
          vin: queryArg.vin,
        },
      }),
    }),
    getDcmGenerationByDcmGeneration: build.query<
      GetDcmGenerationByDcmGenerationApiResponse,
      GetDcmGenerationByDcmGenerationApiArg
    >({
      query: (queryArg) => ({
        url: `/v1/dcmGeneration/${queryArg.dcmGeneration}`,
      }),
    }),
  }),
  overrideExisting: false,
});
export { injectedRtkApi as vehiclesApi };
export type GetVehicleByVinApiResponse = /** status 200 A specific vehicle */ {
  /** Vehicle Identification Number (VIN). Uniquely identifies a vehicle */
  vin: string;
  carlineDescription?: string;
  /** Carline Id */
  carlineId?: number;
  carlineName: string;
  caseNumber?: string;
  /** Used to identify Hawaii vehicles */
  distributorCode?: string;
  /** Serial number of the engine originally installed in this vehicle */
  engineNumber?: string;
  immobilizerKey?: string;
  /** Used by Japan in Helpnet UI only. */
  ispId?: string;
  /** Serial number of the key fob provided with the vehicle */
  keyNumber?: string;
  productionDate?: string;
  telematicsFlag: boolean;
  /** Used by the DCM to uniquely identify the vehicle to the MySubaru customer portal. */
  telematicsVehicleId?: string;
  /** The factory where the vehicle was manufactured */
  vehicleSource?: string;
  capabilities?: string[];
  /** Concatenation of Organization Name and Country Code */
  marketCode: string;
  globalModel?: {
    globalModelCode: string;
    globalExteriorColorCode: string;
    globalInteriorColorCode: string;
    globalOptionCode?: string;
    globalSpecificationCode: string;
    specCertificationVerbiage?: string;
    /** Code used by a market to identify a vehicle's specification */
    specCode?: string;
  };
  localColor?: {
    /** The key to identify the unique local color code. It is defined by the client how to identify unique local color.  It could be the combination of model year, carline, exteriorcolorcode and interiorcolorcode like SOA does. */
    localColorKey: string;
    /** Used by emergency response systems to simply identify a vehicle's color (e.g. BLUE, RED, GREEN) */
    baseColor: string;
    /** Code used by a market to identify the exterior color of a vehicle. */
    exteriorColorCode: string;
    /** Marketing name of the vehicle's exterior color */
    exteriorColorDescription?: string;
    /** Code used by a market to identify the interior color of a vehicle. */
    interiorColorCode: string;
    /** Marketing name of the vehicle's interior color */
    interiorColorDescription?: string;
  };
  model?: {
    modelKey: string;
    bodyStyle?: string;
    dcmGeneration: string;
    driveTrain?: string;
    engineSize?: string;
    headUnitGeneration: string;
    headUnitProviderCode?: string;
    modelCode: string;
    modelDescription?: string;
    modelTrim?: string;
    modelTrimDescription?: string;
    /** Model Year */
    modelYear: number;
    /** Expected values like G for gas, H for PHEV, and E for electric cars. */
    motivePower: string;
    optionCode?: string;
    transmission?: string;
    turbo?: boolean;
    /** Concatenation of Organization Name and Country Code */
    marketCode: string;
  };
};
export type GetVehicleByVinApiArg = {
  /** Vehicle Identification Number (VIN) */
  vin: string;
};
export type GetVehiclesByVinsApiResponse = /** status 200 Vehicles */ {
  /** Vehicle Identification Number (VIN). Uniquely identifies a vehicle */
  vin: string;
  carlineDescription?: string;
  /** Carline Id */
  carlineId?: number;
  carlineName: string;
  caseNumber?: string;
  /** Used to identify Hawaii vehicles */
  distributorCode?: string;
  /** Serial number of the engine originally installed in this vehicle */
  engineNumber?: string;
  immobilizerKey?: string;
  /** Used by Japan in Helpnet UI only. */
  ispId?: string;
  /** Serial number of the key fob provided with the vehicle */
  keyNumber?: string;
  productionDate?: string;
  telematicsFlag: boolean;
  /** Used by the DCM to uniquely identify the vehicle to the MySubaru customer portal. */
  telematicsVehicleId?: string;
  /** The factory where the vehicle was manufactured */
  vehicleSource?: string;
  capabilities?: string[];
  /** Concatenation of Organization Name and Country Code */
  marketCode: string;
  globalModel?: {
    globalModelCode: string;
    globalExteriorColorCode: string;
    globalInteriorColorCode: string;
    globalOptionCode?: string;
    globalSpecificationCode: string;
    specCertificationVerbiage?: string;
    /** Code used by a market to identify a vehicle's specification */
    specCode?: string;
  };
  localColor?: {
    /** The key to identify the unique local color code. It is defined by the client how to identify unique local color.  It could be the combination of model year, carline, exteriorcolorcode and interiorcolorcode like SOA does. */
    localColorKey: string;
    /** Used by emergency response systems to simply identify a vehicle's color (e.g. BLUE, RED, GREEN) */
    baseColor: string;
    /** Code used by a market to identify the exterior color of a vehicle. */
    exteriorColorCode: string;
    /** Marketing name of the vehicle's exterior color */
    exteriorColorDescription?: string;
    /** Code used by a market to identify the interior color of a vehicle. */
    interiorColorCode: string;
    /** Marketing name of the vehicle's interior color */
    interiorColorDescription?: string;
  };
  model?: {
    modelKey: string;
    bodyStyle?: string;
    dcmGeneration: string;
    driveTrain?: string;
    engineSize?: string;
    headUnitGeneration: string;
    headUnitProviderCode?: string;
    modelCode: string;
    modelDescription?: string;
    modelTrim?: string;
    modelTrimDescription?: string;
    /** Model Year */
    modelYear: number;
    /** Expected values like G for gas, H for PHEV, and E for electric cars. */
    motivePower: string;
    optionCode?: string;
    transmission?: string;
    turbo?: boolean;
    /** Concatenation of Organization Name and Country Code */
    marketCode: string;
  };
}[];
export type GetVehiclesByVinsApiArg = {
  /** Vehicle Identification Number (VIN) */
  body: {
    vins: string[];
  };
};
export type GetDcmByVinApiResponse = /** status 200 A specific dcm */ {
  /** DCM Number */
  dcmNumber: string;
  /** ICCID */
  iccid?: string;
  /** IMEI */
  imei?: string;
  /** Manufacturer Code */
  manufacturerCode?: string;
  /** MSISDN */
  msisdn?: string;
  /** Pairing Date */
  pairingDate?: string;
  /** Scrap Date */
  scrapDate?: string;
  /** Shipping Date */
  shippingDate?: string;
};
export type GetDcmByVinApiArg = {
  /** Vehicle Identification Number (VIN) */
  vin: string;
};
export type GetDcmByDcmIdApiResponse = /** status 200 A specific dcm */ {
  /** DCM Number */
  dcmNumber: string;
  /** ICCID */
  iccid?: string;
  /** IMEI */
  imei?: string;
  /** Manufacturer Code */
  manufacturerCode?: string;
  /** MSISDN */
  msisdn?: string;
  /** Pairing Date */
  pairingDate?: string;
  /** Scrap Date */
  scrapDate?: string;
  /** Shipping Date */
  shippingDate?: string;
};
export type GetDcmByDcmIdApiArg = {
  /** Dcm id */
  dcmId: number;
};
export type GetDcmSoftwareByVinApiResponse =
  /** status 200 A specific Dcm Software */ {
    /** Software Version */
    version: string;
    /** Conti R Number */
    contiRNumber?: string;
    /** Official Loads Info */
    officialLoadsInfo?: string;
    /** Release Note */
    releaseNote?: string;
    /** Software Short Version */
    shortVersion?: string;
    dcms?: {
      /** DCM Number */
      dcmNumber: string;
      /** ICCID */
      iccid?: string;
      /** IMEI */
      imei?: string;
      /** Manufacturer Code */
      manufacturerCode?: string;
      /** MSISDN */
      msisdn?: string;
      /** Pairing Date */
      pairingDate?: string;
      /** Scrap Date */
      scrapDate?: string;
      /** Shipping Date */
      shippingDate?: string;
    }[];
    dcmGeneration: {
      /** DCM Generation */
      dcmGeneration: string;
      /** TSP Code */
      tspCode?: string;
      /** Concatenation of Organization Name and Country Code */
      marketCode: string;
    };
  };
export type GetDcmSoftwareByVinApiArg = {
  /** Vehicle Identification Number (VIN) */
  vin: string;
};
export type GetDcmSoftwareByDcmSoftwareApiResponse =
  /** status 200 A specific Dcm Software Id */ {
    /** Software Version */
    version: string;
    /** Conti R Number */
    contiRNumber?: string;
    /** Official Loads Info */
    officialLoadsInfo?: string;
    /** Release Note */
    releaseNote?: string;
    /** Software Short Version */
    shortVersion?: string;
    dcms?: {
      /** DCM Number */
      dcmNumber: string;
      /** ICCID */
      iccid?: string;
      /** IMEI */
      imei?: string;
      /** Manufacturer Code */
      manufacturerCode?: string;
      /** MSISDN */
      msisdn?: string;
      /** Pairing Date */
      pairingDate?: string;
      /** Scrap Date */
      scrapDate?: string;
      /** Shipping Date */
      shippingDate?: string;
    }[];
    dcmGeneration: {
      /** DCM Generation */
      dcmGeneration: string;
      /** TSP Code */
      tspCode?: string;
      /** Concatenation of Organization Name and Country Code */
      marketCode: string;
    };
  };
export type GetDcmSoftwareByDcmSoftwareApiArg = {
  /** Dcm Software Id */
  dcmSoftwareId: number;
};
export type GetModelByVinApiResponse = /** status 200 A specific model */ {
  modelKey: string;
  bodyStyle?: string;
  dcmGeneration: string;
  driveTrain?: string;
  engineSize?: string;
  headUnitGeneration: string;
  headUnitProviderCode?: string;
  modelCode: string;
  modelDescription?: string;
  modelTrim?: string;
  modelTrimDescription?: string;
  /** Model Year */
  modelYear: number;
  /** Expected values like G for gas, H for PHEV, and E for electric cars. */
  motivePower: string;
  optionCode?: string;
  transmission?: string;
  turbo?: boolean;
  /** Concatenation of Organization Name and Country Code */
  marketCode: string;
};
export type GetModelByVinApiArg = {
  /** Vehicle Identification Number (VIN) */
  vin: string;
};
export type GetModelByModelKeyApiResponse = /** status 200 A specific model */ {
  modelKey: string;
  bodyStyle?: string;
  dcmGeneration: string;
  driveTrain?: string;
  engineSize?: string;
  headUnitGeneration: string;
  headUnitProviderCode?: string;
  modelCode: string;
  modelDescription?: string;
  modelTrim?: string;
  modelTrimDescription?: string;
  /** Model Year */
  modelYear: number;
  /** Expected values like G for gas, H for PHEV, and E for electric cars. */
  motivePower: string;
  optionCode?: string;
  transmission?: string;
  turbo?: boolean;
  /** Concatenation of Organization Name and Country Code */
  marketCode: string;
};
export type GetModelByModelKeyApiArg = {
  /** Model Key */
  modelKey: string;
};
export type ListModelCapabilitiesApiResponse =
  /** status 200 successful operation */ ({
    capabilityCode: string;
    description?: string;
    deliveryChecklistFlag?: boolean;
    monroneyLabelFlag?: boolean;
    remoteFlag?: boolean;
  } & {
    effectiveEndDate?: string;
    effectiveStartDate: string;
  })[];
export type ListModelCapabilitiesApiArg = {
  /** Model Key */
  modelKey: string;
};
export type GetCapabilityCodesByModelKeyApiResponse =
  /** status 200 successful operation */ string[];
export type GetCapabilityCodesByModelKeyApiArg = {
  /** Model Key */
  modelKey: string;
  /** Include Inactive Capability Codes */
  includeInactive?: boolean;
};
export type GetCapabilityApiResponse =
  /** status 200 A specific base capability */ {
    capabilityCode: string;
    description?: string;
    deliveryChecklistFlag?: boolean;
    monroneyLabelFlag?: boolean;
    remoteFlag?: boolean;
  };
export type GetCapabilityApiArg = {
  /** Capability Code */
  capabilityCode: string;
};
export type ListCapabilitiesApiResponse =
  /** status 200 A list of base capabilities */ {
    capabilityCode: string;
    description?: string;
    deliveryChecklistFlag?: boolean;
    monroneyLabelFlag?: boolean;
    remoteFlag?: boolean;
  }[];
export type ListCapabilitiesApiArg = void;
export type GetDcmGenerationByVinApiResponse =
  /** status 200 A specific dcm generation */ {
    /** DCM Generation */
    dcmGeneration: string;
    /** TSP Code */
    tspCode?: string;
    /** Concatenation of Organization Name and Country Code */
    marketCode: string;
  };
export type GetDcmGenerationByVinApiArg = {
  /** Vehicle Identification Number (VIN) */
  vin: string;
};
export type GetDcmGenerationByDcmGenerationApiResponse =
  /** status 200 A specific dcm generation */ {
    /** DCM Generation */
    dcmGeneration: string;
    /** TSP Code */
    tspCode?: string;
    /** Concatenation of Organization Name and Country Code */
    marketCode: string;
  };
export type GetDcmGenerationByDcmGenerationApiArg = {
  /** Dcm Generation */
  dcmGeneration: string;
};
export const {
  useGetVehicleByVinQuery,
  useGetVehiclesByVinsQuery,
  useGetDcmByVinQuery,
  useGetDcmByDcmIdQuery,
  useGetDcmSoftwareByVinQuery,
  useGetDcmSoftwareByDcmSoftwareQuery,
  useGetModelByVinQuery,
  useGetModelByModelKeyQuery,
  useListModelCapabilitiesQuery,
  useGetCapabilityCodesByModelKeyQuery,
  useGetCapabilityQuery,
  useListCapabilitiesQuery,
  useGetDcmGenerationByVinQuery,
  useGetDcmGenerationByDcmGenerationQuery,
} = injectedRtkApi;
