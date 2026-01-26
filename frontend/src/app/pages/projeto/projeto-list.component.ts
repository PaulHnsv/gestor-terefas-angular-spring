import { Component, OnInit } from '@angular/core';
import { ProjetoService } from '../../core/services/projeto.service';
import { ProjetoDTO } from '../../pages/projeto/projeto.model';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-projetos-list',
  templateUrl: './projeto-list.component.html',
   imports: [CommonModule, ReactiveFormsModule],
})
export class ProjetosListComponent implements OnInit {
  projetos: ProjetoDTO[] = [];
  carregando = false;
  erro: string | null = null;

  successMessage = '';
  errorMessage = '';
  fieldErrors: Record<string, string> = {};
  loading = false;
  
  form;

  constructor(private fb: FormBuilder, 
    private projetoService: ProjetoService
  ) {
    this.form = this.createForm();
  }

  private createForm() {
    return this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(80)]],
      descricao: ['']
    });
  }
  
  ngOnInit(): void {
    this.carregar();
  }

  carregar(): void {
    this.carregando = true;
    this.erro = null;

    this.projetoService.listar().subscribe({
      next: (data) => {
        console.log('Projetos carregados', data);
        this.projetos = data ?? [];
        this.carregando = false;
      },
      error: (err) => {
        this.erro = 'Falha ao carregar projetos. Verifique API/CORS.';
        this.carregando = false;
        console.error(err);
      }
    });
  }

  get nameCtrl() {
    return this.form.controls.nome;
  }

  submit(): void {
    this.successMessage = '';
    this.errorMessage = '';
    this.fieldErrors = {};

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.loading = true;

    const payload = {
      nome: (this.form.value.nome ?? '').trim(),
      descricao: this.form.value.descricao?.trim() || null
    };

    this.projetoService.create(payload)
      .pipe(finalize(() => (this.loading = false)))
      .subscribe({
        next: (created) => {
          this.successMessage = `Projeto criado: ${created.nome}`;
          this.form.reset({ nome: '', descricao: '' });
        },
        error: (err) => {
          // Tentativa de ler padrão comum de validação (ajuste pro seu backend)
          const body = err?.error;

          // Exemplo 1: { message: "...", fieldErrors: { name: "obrigatório" } }
          if (body?.fieldErrors) {
            this.fieldErrors = body.fieldErrors;
            this.errorMessage = body.message || 'Erro de validação.';
            return;
          }

          // Exemplo 2: Spring validation padrão pode vir como { errors: [...] }
          // (depende de como você implementou)
          this.errorMessage = body?.message || 'Não foi possível criar o projeto.';
        }
      });
}
}
