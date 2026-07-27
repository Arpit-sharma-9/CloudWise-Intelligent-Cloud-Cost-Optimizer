import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';

import { routes } from './app.routes';

/**
 * Root application configuration for the standalone Angular app.
 *
 * Provides:
 *  - Zone.js change detection
 *  - Router with the routes defined in app.routes.ts
 *  - HttpClient so services can call the Spring Boot REST API
 */
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient()
  ]
};
