import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { CloudResourceService } from '../services/cloud-resource.service';
import { CloudResource } from '../models/cloud-resource.model';
import { StatusBadgeComponent } from '../shared/status-badge.component';

/**
 * ResourcesComponent shows all cloud resources in a table with
 * Edit and Delete actions on each row.
 */
@Component({
  selector: 'app-resources',
  standalone: true,
  imports: [CommonModule, RouterLink, StatusBadgeComponent],
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.css']
})
export class ResourcesComponent implements OnInit {
  private readonly resourceService = inject(CloudResourceService);

  resources: CloudResource[] = [];
  loading = true;
  errorMessage = '';
  successMessage = '';

  trackById = (_: number, r: CloudResource) => r.id;

  ngOnInit(): void {
    this.loadResources();
  }

  /** Fetches all resources from the backend. */
  loadResources(): void {
    this.loading = true;
    this.errorMessage = '';
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
   * Asks the user to confirm, then deletes the resource.
   * Shows a success message after deletion and refreshes the list.
   */
  deleteResource(resource: CloudResource): void {
    const confirmed = confirm(
      `Are you sure you want to delete "${resource.resourceName}"?`
    );
    if (!confirmed) return;

    this.successMessage = '';
    this.errorMessage = '';

    this.resourceService.delete(resource.id).subscribe({
      next: () => {
        this.successMessage = `Deleted "${resource.resourceName}" successfully.`;
        this.loadResources();
      },
      error: (err) => {
        this.errorMessage = err.message;
      }
    });
  }

  /** Formats a number as Indian Rupees. */
  formatCurrency(value: number): string {
    return '₹' + (value ?? 0).toLocaleString('en-IN', { maximumFractionDigits: 2 });
  }
}
