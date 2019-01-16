import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    RmsDepSdmSuffixComponent,
    RmsDepSdmSuffixDetailComponent,
    RmsDepSdmSuffixUpdateComponent,
    RmsDepSdmSuffixDeletePopupComponent,
    RmsDepSdmSuffixDeleteDialogComponent,
    rmsDepRoute,
    rmsDepPopupRoute
} from './';

const ENTITY_STATES = [...rmsDepRoute, ...rmsDepPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RmsDepSdmSuffixComponent,
        RmsDepSdmSuffixDetailComponent,
        RmsDepSdmSuffixUpdateComponent,
        RmsDepSdmSuffixDeleteDialogComponent,
        RmsDepSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        RmsDepSdmSuffixComponent,
        RmsDepSdmSuffixUpdateComponent,
        RmsDepSdmSuffixDeleteDialogComponent,
        RmsDepSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbRmsDepSdmSuffixModule {}
