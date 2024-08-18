import { Routes } from '@angular/router';
import { ClientFormComponent } from './client/client-form/client-form.component';
import { ClientResultsComponent } from './client/client-results/client-results.component';

export const routes: Routes = [
    { path: '', redirectTo: '/client-form', pathMatch: 'full' },
    { path: 'client-form', component: ClientFormComponent},
    { path: 'client-result', component: ClientResultsComponent},
    { path: '**', redirectTo: '/document-form'}
];
