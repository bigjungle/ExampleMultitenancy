import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IParaSourceSdmSuffix } from 'app/shared/model/para-source-sdm-suffix.model';
import { ParaSourceSdmSuffixService } from './para-source-sdm-suffix.service';

@Component({
    selector: 'jhi-para-source-sdm-suffix-delete-dialog',
    templateUrl: './para-source-sdm-suffix-delete-dialog.component.html'
})
export class ParaSourceSdmSuffixDeleteDialogComponent {
    paraSource: IParaSourceSdmSuffix;

    constructor(
        protected paraSourceService: ParaSourceSdmSuffixService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.paraSourceService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'paraSourceListModification',
                content: 'Deleted an paraSource'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-para-source-sdm-suffix-delete-popup',
    template: ''
})
export class ParaSourceSdmSuffixDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ paraSource }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ParaSourceSdmSuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.paraSource = paraSource;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
