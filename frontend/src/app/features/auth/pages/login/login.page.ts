import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { LoginRequest } from 'src/app/core/models/login-request.model';
import { AuthService } from 'src/app/core/services/auth.service';
import { FormState } from 'src/app/shared/interfaces/FormState';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class Login {
  loginForm;
  showPassword = false;

  state: FormState = { status: 'idle', fieldErrors: {} };

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
  ) {
    this.loginForm = this.createForm();
  }

  private createForm() {
    return this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  get loading() {
    return this.state.status === 'loading';
  }

  get fieldErrors() {
    return this.state.fieldErrors;
  }

  onSubmit() {
    if (this.loginForm.invalid) return;

    this.state = { status: 'loading', fieldErrors: {} };

    this.authService.login(this.loginForm.value as LoginRequest).subscribe({
      next: () => {
        this.state = { status: 'success', fieldErrors: {} };
        this.router.navigate(['/home']);
      },
      error: (err) => {
        const message =
          err.status === 401
            ? 'Usuário ou senha incorretos.'
            : 'Erro ao realizar login. Tente novamente.';
        this.state = { status: 'error', errorMessage: message, fieldErrors: {} };
      },
    });
  }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }
}
