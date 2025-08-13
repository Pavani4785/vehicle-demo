import {createApi} from "@reduxjs/toolkit/query/react"
import {fetchBaseQuery} from "@reduxjs/toolkit/query/react"

// initialize an empty api service that we'll inject endpoints into later as needed
export const emptySplitApi = createApi({
  reducerPath: 'vehicleApi',
  baseQuery: fetchBaseQuery({baseUrl: 'http://localhost:8080/'}), endpoints: () => ({}),
})
