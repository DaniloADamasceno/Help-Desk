import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";
import {Technician} from "../../../../models/technician";

@Component({
    selector: 'app-technician-list',
    templateUrl: './technician-list.component.html',
    styleUrls: ['./technician-list.component.css']
})
export class TechnicianListComponent implements OnInit {

    ELEMENT_DATA: Technician[] = [
        {
            id: 1,
            name: 'Tecnico de Testes TypeScript',
            cpf: '123.456.789-10',
            email: 'teste@testes.com',
            password: '123456',
            role: ['0'],
            creationDate: '06/11/2023'
        }
    ];

    displayedColumns: string[] = ['position', 'name', 'weight', 'symbol', 'actions'];
    dataSource = new MatTableDataSource<Technician>(this.ELEMENT_DATA);

    constructor() {
    }

    ngOnInit(): void {
    }

    @ViewChild(MatPaginator) paginator: MatPaginator;

    ngAfterViewInit() {
        this.dataSource.paginator = this.paginator;
    }
}

