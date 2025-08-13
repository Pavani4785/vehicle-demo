package com.subaru.cv.vehicleservice;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Spring Doc to generate OpenAPI documentation.
 * Ideally this would be picked up from the spec file, but for now we define it again here.
 * This should align with the OpenAPI spec in the project root.
 */
@Configuration
class SpringdocConfiguration {

  /**
   * OpenAPI spec info for Spring Doc. Allows authorization in the UI via OAuth2 with Cognito.
   *
   * @return OpenAPI instance with API info and security scheme
   */
  @Bean(name = "org.openapitools.configuration.SpringDocConfiguration.apiInfo")
  OpenAPI apiInfo() {
    final String cognitoAuthorizationUrl = "https://soa-cv-dev-use1-preprod.auth.us-east-1.amazoncognito.com/login";
    final String cognitoTokenUrl = "https://soa-cv-dev-use1-preprod.auth.us-east-1.amazoncognito.com/oauth2/token";
    final String cognitoRefreshUrl = "https://soa-cv-dev-use1-preprod.auth.us-east-1.amazoncognito.com/oauth2/token";
    final String securitySchemeName = "jwtAuthorizer";

    return new OpenAPI()
        .info(
            new Info()
                .title("CV Vehicle API")
                .description("Connected Vehicle Vehicle API")
                .version("1.0.0")
        )
        .components(
            new Components()
                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                    .type(SecurityScheme.Type.OAUTH2)
                    .flows(new OAuthFlows()
                        .authorizationCode(new OAuthFlow()
                            .authorizationUrl(cognitoAuthorizationUrl)
                            .tokenUrl(cognitoTokenUrl)
                            .refreshUrl(cognitoRefreshUrl)
                            .scopes(new Scopes())
                        )
                    )
                )
        );
  }
}
