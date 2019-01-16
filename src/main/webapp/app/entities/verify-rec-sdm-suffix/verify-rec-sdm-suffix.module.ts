import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    VerifyRecSdmSuffixComponent,
    VerifyRecSdmSuffixDetailComponent,
    VerifyRecSdmSuffixUpdateComponent,
    VerifyRecSdmSuffixDeletePopupComponent,
    VerifyRecSdmSuffixDeleteDialogComponent,
    verifyRecRoute,
    verifyRecPopupRoute
} from './';

const ENTITY_STATES = [...verifyRecRoute, ...verifyRecPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VerifyRecSdmSuffixComponent,
        VerifyRecSdmSuffixDetailComponent,
        VerifyRecSdmSuffixUpdateComponent,
        VerifyRecSdmSuffixDeleteDialogComponent,
        VerifyRecSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        VerifyRecSdmSuffixComponent,
        VerifyRecSdmSuffixUpdateComponent,
        VerifyRecSdmSuffixDeleteDialogComponent,
        VerifyRecSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbVerifyRecSdmSuffixModule {}
