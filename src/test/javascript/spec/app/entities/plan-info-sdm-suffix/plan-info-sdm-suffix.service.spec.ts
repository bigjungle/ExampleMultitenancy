/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PlanInfoSdmSuffixService } from 'app/entities/plan-info-sdm-suffix/plan-info-sdm-suffix.service';
import { IPlanInfoSdmSuffix, PlanInfoSdmSuffix, ValidType, ViewPermission } from 'app/shared/model/plan-info-sdm-suffix.model';

describe('Service Tests', () => {
    describe('PlanInfoSdmSuffix Service', () => {
        let injector: TestBed;
        let service: PlanInfoSdmSuffixService;
        let httpMock: HttpTestingController;
        let elemDefault: IPlanInfoSdmSuffix;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(PlanInfoSdmSuffixService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new PlanInfoSdmSuffix(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'image/png',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'image/png',
                'AAAAAAA',
                'AAAAAAA',
                false,
                ValidType.LONG,
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                false,
                'AAAAAAA',
                0,
                ViewPermission.PRIVATEVIEW,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        publishedTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a PlanInfoSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        publishedTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        publishedTime: currentDate,
                        verifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new PlanInfoSdmSuffix(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a PlanInfoSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        sortString: 'BBBBBB',
                        descString: 'BBBBBB',
                        jsonString: 'BBBBBB',
                        remarks: 'BBBBBB',
                        refEvent: 'BBBBBB',
                        attachmentPath: 'BBBBBB',
                        attachmentBlob: 'BBBBBB',
                        attachmentName: 'BBBBBB',
                        textBlob: 'BBBBBB',
                        imageBlob: 'BBBBBB',
                        imageBlobName: 'BBBBBB',
                        usingFlag: true,
                        validType: 'BBBBBB',
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        publishedTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyNeed: true,
                        verifyOpinion: 'BBBBBB',
                        viewCount: 1,
                        viewPermission: 'BBBBBB',
                        viewPermPersion: 'BBBBBB',
                        serialNumber: 'BBBBBB',
                        versionNumber: 'BBBBBB',
                        paraSourceString: 'BBBBBB',
                        featureKeyword: 'BBBBBB',
                        suggestion: 'BBBBBB',
                        releaseScope: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        publishedTime: currentDate,
                        verifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of PlanInfoSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        sortString: 'BBBBBB',
                        descString: 'BBBBBB',
                        jsonString: 'BBBBBB',
                        remarks: 'BBBBBB',
                        refEvent: 'BBBBBB',
                        attachmentPath: 'BBBBBB',
                        attachmentBlob: 'BBBBBB',
                        attachmentName: 'BBBBBB',
                        textBlob: 'BBBBBB',
                        imageBlob: 'BBBBBB',
                        imageBlobName: 'BBBBBB',
                        usingFlag: true,
                        validType: 'BBBBBB',
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        publishedTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyNeed: true,
                        verifyOpinion: 'BBBBBB',
                        viewCount: 1,
                        viewPermission: 'BBBBBB',
                        viewPermPersion: 'BBBBBB',
                        serialNumber: 'BBBBBB',
                        versionNumber: 'BBBBBB',
                        paraSourceString: 'BBBBBB',
                        featureKeyword: 'BBBBBB',
                        suggestion: 'BBBBBB',
                        releaseScope: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        publishedTime: currentDate,
                        verifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a PlanInfoSdmSuffix', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
