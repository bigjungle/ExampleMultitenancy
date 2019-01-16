import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    RmsPersonSdmSuffixComponent,
    RmsPersonSdmSuffixDetailComponent,
    RmsPersonSdmSuffixUpdateComponent,
    RmsPersonSdmSuffixDeletePopupComponent,
    RmsPersonSdmSuffixDeleteDialogComponent,
    rmsPersonRoute,
    rmsPersonPopupRoute
} from './';

const ENTITY_STATES = [...rmsPersonRoute, ...rmsPersonPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RmsPersonSdmSuffixComponent,
        RmsPersonSdmSuffixDetailComponent,
        RmsPersonSdmSuffixUpdateComponent,
        RmsPersonSdmSuffixDeleteDialogComponent,
        RmsPersonSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        RmsPersonSdmSuffixComponent,
        RmsPersonSdmSuffixUpdateComponent,
        RmsPersonSdmSuffixDeleteDialogComponent,
        RmsPersonSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbRmsPersonSdmSuffixModule {}
