import { Routes } from '@angular/router';
import { HomePage } from './features/home/pages/home.page';
import { ProjetosListPage } from './features/projeto/pages/projeto-list/projeto-list.page';
import { TarefasListPage } from './features/tarefa/pages/tarefa-list/tarefa-list.page';
import { Login } from './features/auth/pages/login/login.page';
import { authGuard } from './core/guards/auth.guard';
import { loginGuard } from './core/guards/login.guard';

export const routes: Routes = [
  { path: 'home', component: HomePage, canActivate: [authGuard] },
  { path: 'projects', component: ProjetosListPage, canActivate: [authGuard] },
  { path: 'tasks', component: TarefasListPage, canActivate: [authGuard] },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login, pathMatch: 'full', canActivate: [loginGuard] },
  { path: '**', redirectTo: 'login' },
];
