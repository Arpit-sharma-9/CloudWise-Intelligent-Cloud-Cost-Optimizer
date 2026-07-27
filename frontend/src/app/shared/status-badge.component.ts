import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

/**
 * StatusBadgeComponent renders a small colored badge for a resource's
 * status (Running / Stopped) or for its recommendation
 * (High Cost Resource / Normal Resource).
 *
 * The color is chosen based on the text value so the UI is more visual
 * without needing any complex logic.
 */
@Component({
  selector: 'app-status-badge',
  standalone: true,
  imports: [CommonModule],
  template: `
    <span class="badge" [ngClass]="badgeClass">{{ value }}</span>
  `,
  styles: [`
    .badge {
      display: inline-block;
      padding: 3px 10px;
      border-radius: 12px;
      font-size: 12px;
      font-weight: 500;
      white-space: nowrap;
    }
    .badge-running {
      background-color: #e8f5e9;
      color: #2e7d32;
    }
    .badge-stopped {
      background-color: #fbe9e7;
      color: #c62828;
    }
    .badge-high {
      background-color: #fff3e0;
      color: #e65100;
    }
    .badge-normal {
      background-color: #e3f2fd;
      color: #1565c0;
    }
    .badge-default {
      background-color: #eceff1;
      color: #455a64;
    }
  `]
})
export class StatusBadgeComponent {
  /** The text to display inside the badge. */
  @Input() value = '';

  /**
   * Picks a CSS class based on the value of the input.
   * Running/Stopped  -> status badges
   * High Cost/Normal -> recommendation badges
   */
  get badgeClass(): string {
    const v = (this.value || '').toLowerCase();
    if (v === 'running') return 'badge-running';
    if (v === 'stopped') return 'badge-stopped';
    if (v.includes('high')) return 'badge-high';
    if (v.includes('normal')) return 'badge-normal';
    return 'badge-default';
  }
}
