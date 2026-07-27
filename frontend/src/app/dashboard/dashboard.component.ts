import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { CloudResourceService } from '../services/cloud-resource.service';
import { CloudResource, DashboardStats } from '../models/cloud-resource.model';
import { StatusBadgeComponent } from '../shared/status-badge.component';

/**
 * DashboardComponent displays:
 *   - Four summary cards (Total / Running / Stopped / Monthly Cost)
 *   - A table listing all cloud resources
 *
 * The data is fetched once on init by calling the backend dashboard
 * endpoint and the resources endpoint.
 */
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink, StatusBadgeComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private readonly resourceService = inject(CloudResourceService);

  /** Aggregated statistics shown in the four cards. */
  stats: DashboardStats = {
    totalResources: 0,
    runningResources: 0,
    stoppedResources: 0,
    totalMonthlyCost: 0
  };

  /** All resources shown in the table below the cards. */
  resources: CloudResource[] = [];

  /** Flag for showing a loading state. */
  loading = true;

  /** Error message to display if the API call fails. */
  errorMessage = '';

  /** Track the table rows by resource id so Angular re-renders efficiently. */
  trackById = (_: number, r: CloudResource) => r.id;

  ngOnInit(): void {
    this.loadDashboard();
  }

  /**
   * Loads both the dashboard statistics and the full list of resources.
   * They are loaded independently so a failure in one does not block the other.
   */
  loadDashboard(): void {
    this.loading = true;
    this.errorMessage = '';

    this.resourceService.getDashboard().subscribe({
      next: (data) => {
        this.stats = data;
      },
      error: (err) => {
        this.errorMessage = err.message;
        this.loading = false;
      }
    });

    this.resourceService.getAll().subscribe({
      next: (data) => {
        this.resources = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = err.message;
        this.loading = false;
      }
    });
  }

  /**
   * Formats a number as Indian Rupees with thousands separators.
   */
  formatCurrency(value: number): string {
    return '₹' + (value ?? 0).toLocaleString('en-IN', { maximumFractionDigits: 2 });
  }
}
