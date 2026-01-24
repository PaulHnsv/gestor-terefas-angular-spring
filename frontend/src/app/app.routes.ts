import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ProjetosListComponent } from './pages/projeto/projeto-list.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'projects', component: ProjetosListComponent },
    { path: '', redirectTo: 'projects', pathMatch: 'full' }
];
