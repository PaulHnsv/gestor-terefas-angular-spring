import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppStatusService } from 'src/app/core/services/app-status.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {

ping$!: Observable<{ message: string; timestamp: string }>;
versionInfo$!: Observable<{ version: string; name: string; timestamp: string }>;

  constructor(private appStatusService: AppStatusService) {}

  ngOnInit(): void {
  this.ping$ = this.appStatusService.getPing();
  this.versionInfo$ = this.appStatusService.getVersion();
  }
}
