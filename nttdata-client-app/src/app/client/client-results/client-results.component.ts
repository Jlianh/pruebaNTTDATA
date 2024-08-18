import { Component } from '@angular/core';
import { ClientService } from '../client-service/client.service';
import { ActivatedRoute, RouterModule } from '@angular/router';

@Component({
  selector: 'app-client-results',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './client-results.component.html',
  styleUrl: './client-results.component.css'
})
export class ClientResultsComponent {

  clientParameters!: {};

  clientData: any;

  constructor(private clientService: ClientService, private router: ActivatedRoute) { }

  ngOnInit(): void {
    this.getUrlParams();
    this.getClientData();
  }

  getUrlParams(): void {
    this.router.queryParams.subscribe(
      params => {
        this.clientParameters = params;
      }
    )
  }

  getClientData(): void {
    this.clientService.getClientByDocumentTypeAndDocument(this.clientParameters).subscribe(
      (data) => {
        this.clientData = data;
        console.log(this.clientData);
      }
    );
  }
}
