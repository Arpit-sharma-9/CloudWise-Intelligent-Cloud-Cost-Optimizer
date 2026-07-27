import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import {
  CloudResource,
  CloudResourceRequest,
  DashboardStats,
  ApiError
} from '../models/cloud-resource.model';

/**
 * CloudResourceService is the single entry point the Angular components
 * use to talk to the Spring Boot REST API.
 *
 * Each method corresponds to one REST endpoint:
 *   getAll()        -> GET    /api/resources
 *   getById(id)     -> GET    /api/resources/{id}
 *   create(body)    -> POST   /api/resources
 *   update(id,body) -> PUT    /api/resources/{id}
 *   delete(id)      -> DELETE /api/resources/{id}
 *   getDashboard()  -> GET    /api/dashboard
 *
 * Errors are caught and re-thrown as readable strings so the components
 * can simply display them to the user.
 */
@Injectable({ providedIn: 'root' })
export class CloudResourceService {
  private http = inject(HttpClient);
  private readonly baseUrl = environment.apiUrl;

  /** GET /api/resources */
  getAll(): Observable<CloudResource[]> {
    return this.http
      .get<CloudResource[]>(`${this.baseUrl}/resources`)
      .pipe(catchError(this.handleError));
  }

  /** GET /api/resources/{id} */
  getById(id: number): Observable<CloudResource> {
    return this.http
      .get<CloudResource>(`${this.baseUrl}/resources/${id}`)
      .pipe(catchError(this.handleError));
  }

  /** POST /api/resources */
  create(payload: CloudResourceRequest): Observable<CloudResource> {
    return this.http
      .post<CloudResource>(`${this.baseUrl}/resources`, payload)
      .pipe(catchError(this.handleError));
  }

  /** PUT /api/resources/{id} */
  update(id: number, payload: CloudResourceRequest): Observable<CloudResource> {
    return this.http
      .put<CloudResource>(`${this.baseUrl}/resources/${id}`, payload)
      .pipe(catchError(this.handleError));
  }

  /** DELETE /api/resources/{id} */
  delete(id: number): Observable<void> {
    return this.http
      .delete<void>(`${this.baseUrl}/resources/${id}`)
      .pipe(catchError(this.handleError));
  }

  /** GET /api/dashboard */
  getDashboard(): Observable<DashboardStats> {
    return this.http
      .get<DashboardStats>(`${this.baseUrl}/dashboard`)
      .pipe(catchError(this.handleError));
  }

  /**
   * Central error handler.
   * Extracts the backend ApiError.message when available, otherwise falls
   * back to the HTTP status text.
   */
  private handleError(error: HttpErrorResponse) {
    let message = 'An unexpected error occurred.';

    if (error.error instanceof ErrorEvent) {
      // Client-side or network error
      message = `Network error: ${error.error.message}`;
    } else if (error.error && (error.error as ApiError).message) {
      // Backend returned a structured ApiError JSON
      message = (error.error as ApiError).message;
    } else if (error.status === 0) {
      message = 'Cannot connect to the server. Is the backend running on http://localhost:8080?';
    } else if (error.status === 404) {
      message = 'Resource not found.';
    } else if (error.status === 400) {
      message = 'Invalid input. Please check the form values.';
    }

    return throwError(() => new Error(message));
  }
}
