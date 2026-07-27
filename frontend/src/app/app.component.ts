import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';

/**
 * AppComponent is the root component of the CloudWise frontend.
 *
 * It renders:
 *  - A blue header with the application name and navigation links
 *  - A <router-outlet> where the routed pages appear
 *  - A simple footer
 */
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  /** Application name shown in the header. */
  readonly title = 'CloudWise';
  readonly subtitle = 'Intelligent Cloud Cost Optimizer';
}
