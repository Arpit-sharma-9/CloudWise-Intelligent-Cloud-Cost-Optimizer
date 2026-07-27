import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

import { CloudResourceService } from '../services/cloud-resource.service';
import { CloudResourceRequest } from '../models/cloud-resource.model';

/**
 * EditResourceComponent loads an existing resource by id (read from
 * the route), pre-fills the form, and submits the updated values.
 *
 * The id is read from the URL: /resources/edit/:id
 */
@Component({
  selector: 'app-edit-resource',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './edit-resource.component.html',
  styleUrls: ['./edit-resource.component.css']
})
export class EditResourceComponent implements OnInit {
  private readonly resourceService = inject(CloudResourceService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  resourceId = 0;
  loading = true;
  submitting = false;
  errorMessage = '';

  model: CloudResourceRequest = {
    resourceName: '',
    resourceType: 'EC2',
    region: 'Mumbai',
    status: 'Running',
    monthlyCost: 0
  };

  readonly resourceTypes = ['EC2', 'S3', 'RDS', 'ELB', 'EBS', 'Lambda'];
  readonly regions = ['Mumbai', 'Delhi'];
  readonly statuses = ['Running', 'Stopped'];

  ngOnInit(): void {
    // Read the id from the route and load the resource
    const idParam = this.route.snapshot.paramMap.get('id');
    const id = Number(idParam);

    if (!idParam || isNaN(id)) {
      this.errorMessage = 'Invalid resource id.';
      this.loading = false;
      return;
    }

    this.resourceId = id;
    this.loadResource();
  }

  /** Fetches the existing resource and pre-fills the form. */
  loadResource(): void {
    this.resourceService.getById(this.resourceId).subscribe({
      next: (data) => {
        this.model = {
          resourceName: data.resourceName,
          resourceType: data.resourceType,
          region: data.region,
          status: data.status,
          monthlyCost: data.monthlyCost
        };
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = err.message;
        this.loading = false;
      }
    });
  }

  /** Submits the updated resource. */
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

    this.resourceService.update(this.resourceId, this.model).subscribe({
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
