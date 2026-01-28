import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/page/home.component';
import { ProjetosListComponent } from './features/projeto/pages/projeto-list/projeto-list.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'projects', component: ProjetosListComponent },
    { path: '', redirectTo: 'projects', pathMatch: 'full' }
];
