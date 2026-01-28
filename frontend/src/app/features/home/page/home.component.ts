import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppStatusService } from 'src/app/core/services/app-status.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
})
export class HomeComponent implements OnInit {

  ping?: { message: string; timestamp: string };
  versionInfo?: { version: string; name: string; timestamp: string };

  constructor(private appStatusService: AppStatusService) {}

  ngOnInit(): void {
    this.appStatusService.getPing().subscribe(r => {
      console.log('PING', r);
      this.ping = r;
    });

    this.appStatusService.getVersion().subscribe(r => {
      console.log('VERSION', r);
      this.versionInfo = r;
    });
  }
}
