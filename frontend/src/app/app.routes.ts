import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ResourcesComponent } from './resources/resources.component';
import { AddResourceComponent } from './add-resource/add-resource.component';
import { EditResourceComponent } from './edit-resource/edit-resource.component';

/**
 * Application routes.
 *
 * Each path maps to a standalone component that is lazily or eagerly
 * loaded when the user navigates to that URL.
 */
export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent, title: 'Dashboard - CloudWise' },
  { path: 'resources', component: ResourcesComponent, title: 'Resources - CloudWise' },
  { path: 'resources/add', component: AddResourceComponent, title: 'Add Resource - CloudWise' },
  { path: 'resources/edit/:id', component: EditResourceComponent, title: 'Edit Resource - CloudWise' },
  { path: '**', redirectTo: 'dashboard' }
];
