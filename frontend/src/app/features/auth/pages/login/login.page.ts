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
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value as LoginRequest).subscribe({
        next: () => this.router.navigate(['/home']),
        error: () => alert('Login inválido'),
      });
    }
  }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }
}
