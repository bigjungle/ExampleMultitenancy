/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlandbTestModule } from '../../../test.module';
import { ParaCatSdmSuffixDeleteDialogComponent } from 'app/entities/para-cat-sdm-suffix/para-cat-sdm-suffix-delete-dialog.component';
import { ParaCatSdmSuffixService } from 'app/entities/para-cat-sdm-suffix/para-cat-sdm-suffix.service';

describe('Component Tests', () => {
    describe('ParaCatSdmSuffix Management Delete Component', () => {
        let comp: ParaCatSdmSuffixDeleteDialogComponent;
        let fixture: ComponentFixture<ParaCatSdmSuffixDeleteDialogComponent>;
        let service: ParaCatSdmSuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlandbTestModule],
                declarations: [ParaCatSdmSuffixDeleteDialogComponent]
            })
                .overrideTemplate(ParaCatSdmSuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ParaCatSdmSuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ParaCatSdmSuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
