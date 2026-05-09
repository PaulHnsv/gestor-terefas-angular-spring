import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { RegisterRequest } from 'src/app/core/models/register-request.model';
import { FormState } from 'src/app/shared/interfaces/FormState';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage {
  registerForm;
  showPassword = false;
  showConfirmPassword = false;

  state: FormState = { status: 'idle', fieldErrors: {} };
  

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
  ) {
    this.registerForm = this.createForm();
  }

  private createForm() {
    return this.fb.group({
      username: this.fb.control<string>('', [Validators.required]),
      password: this.fb.control<string>('', [Validators.required, Validators.minLength(6)]),
      confirmPassword: this.fb.control<string>('', [Validators.required, this.passwordsMatch.bind(this)]),
    });
  }

  get f() {
    return this.registerForm.controls;
  }

  get loading() {
    return this.state.status === 'loading';
  }

  get fieldErrors() {
    return this.state.fieldErrors;
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.authService.register(this.registerForm.value as RegisterRequest).subscribe({
        next: () => this.router.navigate(['/login']),
        error: () => alert('Registro inválido'),
      });
    }
  }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  toggleConfirmPassword(): void {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

  passwordsMatch(control: any) {
    if (!this.registerForm) return null; 

    const password = this.f.password.value;
    const confirmPassword = control.value;
    return password === confirmPassword ? null : { mismatch: true };
  }

}
