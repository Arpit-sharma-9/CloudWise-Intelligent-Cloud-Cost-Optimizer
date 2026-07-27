/**
 * CloudResource model.
 *
 * Mirrors the CloudResourceResponse DTO returned by the Spring Boot backend.
 * The recommendation field is included here so the frontend can display it
 * in the table even though the user never types it in.
 */
export interface CloudResource {
  id: number;
  resourceName: string;
  resourceType: string;
  region: string;
  status: string;
  monthlyCost: number;
  recommendation: string;
  createdDate?: string;
}

/**
 * Payload sent to the backend when creating or updating a resource.
 * Notice that recommendation is NOT here - the backend generates it.
 */
export interface CloudResourceRequest {
  resourceName: string;
  resourceType: string;
  region: string;
  status: string;
  monthlyCost: number;
}

/**
 * Dashboard statistics returned by GET /api/dashboard.
 */
export interface DashboardStats {
  totalResources: number;
  runningResources: number;
  stoppedResources: number;
  totalMonthlyCost: number;
}

/**
 * Standard error body returned by the backend's GlobalExceptionHandler.
 */
export interface ApiError {
  timestamp: string;
  status: number;
  message: string;
}
