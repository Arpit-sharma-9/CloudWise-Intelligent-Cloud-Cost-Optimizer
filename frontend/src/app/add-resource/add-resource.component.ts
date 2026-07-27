import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { CloudResourceService } from '../services/cloud-resource.service';
import { CloudResourceRequest } from '../models/cloud-resource.model';

/**
 * AddResourceComponent shows a simple form for creating a new cloud
 * resource. The recommendation field is intentionally NOT in the form
 * because the backend generates it automatically based on the monthly cost.
 */
@Component({
  selector: 'app-add-resource',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './add-resource.component.html',
  styleUrls: ['./add-resource.component.css']
})
export class AddResourceComponent {
  private readonly resourceService = inject(CloudResourceService);
  private readonly router = inject(Router);

  /** Form model. */
  model: CloudResourceRequest = {
    resourceName: '',
    resourceType: 'EC2',
    region: 'Mumbai',
    status: 'Running',
    monthlyCost: 0
  };

  /** Dropdown options - kept simple to match the sample data. */
  readonly resourceTypes = ['EC2', 'S3', 'RDS', 'ELB', 'EBS', 'Lambda'];
  readonly regions = ['Mumbai', 'Delhi'];
  readonly statuses = ['Running', 'Stopped'];

  submitting = false;
  errorMessage = '';

  /**
   * Validates basic rules and submits the form by calling the backend.
   * On success, navigates back to the resources list.
   */
  submit(): void {
    this.errorMessage = '';

    if (!this.model.resourceName.trim()) {
      this.errorMessage = 'Resource name is required.';
      return;
    }
    if (this.model.monthlyCost === null || this.model.monthlyCost === undefined || this.model.monthlyCost < 0) {
      this.errorMessage = 'Monthly cost must be a positive number.';
      return;
    }

    this.submitting = true;

    this.resourceService.create(this.model).subscribe({
      next: () => {
        this.router.navigate(['/resources']);
      },
      error: (err) => {
        this.errorMessage = err.message;
        this.submitting = false;
      }
    });
  }
}
