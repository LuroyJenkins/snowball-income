package org.starkov.tests.api.models.watchlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class WatchlistValueModel {
    public String watchlistId;
    public Object watchlistName;
    public Object color;
    public int order;
    public Object priceHighlightMin;
    public Object priceHighlightMax;
    public double customPeriodChangeAmount;
    public double customPeriodChangePercent;
    public ArrayList<CustomPeriodSeries> customPeriodSeries;
    public Object priceBuyAllTime;
    public Object priceSellAllTime;
    public Object parentPieId;
    public int portfolioAllocation;
    public int totalShare;
    public int allocation;
    public int currentAllocation;
    public Object category;
    public int buyValue;
    public int buyValueOfSoldItems;
    public int divPaid;
    public int divTaxes;
    public int commissionPaid;
    public int profitFromSell;
    public int nkdFromSell;
    public int incomeFromNKD;
    @JsonProperty("return")
    public Object myreturn;
    public Object returnBuysOnly;
    public double totalGainValue;
    public String logoURL;
    public String primaryLogoURL;
    public int totalGainPercent;
    public int dividendTax;
    public boolean fromPortfolioDividendTax;
    public String originalCurrency;
    public double originalCurrentPrice;
    public double invariantTotalPrice;
    public String countryISO;
    public String industry;
    public double eps;
    public double pe;
    public double payout;
    public double marketCapMln;
    public String marketCapName;
    public Object xirr;
    public double beta;
    public int divYieldTTM;
    public int divAmountPerShareTTM;
    public int divRating;
    public Date nexDividendDate;
    public double nextDividendPerShare;
    public double nexDividendAmount;
    public Date exDividendDate;
    public Date declaredDate;
    public Object maturityDate;
    public Object offerDate;
    public Object effectiveYield;
    public Object nkd;
    public Object nominal;
    public Object realNominal;
    public Object yieldCoupon;
    public Object currentYield;
    public Object modifCurrentYield;
    public Object yieldToMaturity;
    public Object listingLevel;
    public Object couponsSumm;
    public Object duration;
    public Object bondType;
    public Object term;
    public Object yieldToMaturityPortfolio;
    public Object effectiveYieldPortfolio;
    public String portfolioId;
    public int type;
    public Object pieId;
    public String tickerWithExchange;
    public String description;
    public String anotherName;
    public String ticker;
    public String sector;
    public String exchange;
    public int amount;
    public int price;
    public int pricePercent;
    public int avgNKD;
    public int avgNominal;
    public int totalPrice;
    public boolean excludeNkdFromTotal;
    public double currentPrice;
    public Object currentNKD;
    public Object currentNominal;
    public Object currentPricePercent;
    public int amortizationReceived;
    public Object avgAmortizationReceived;
    public int reinvestedCost;
    public double currentTotalPrice;
    public double gainValue;
    public int gainPercent;
    public String currency;
    public double yearDivPerShare;
    public double yearTotalDivs;
    public double divYieldCurrent;
    public int divYieldAverage;
    public double divYearGrowth;
    public int divFrequency;
    public String assetInfoId;
    public Object note;
    public Object periodGainsAmountAbsolute;
    public double periodGainsAmountPortfolio;
    public double periodGainsAmountPerShare;
    public double periodGainsPercent;
    public boolean soldOut;
    public Object secCurr;
    public boolean ignCurr;
    public Object externalId;
    @JsonProperty("isAutomaticallyCreated")
    public boolean isAutomaticallyCreated;
    @JsonProperty("isValid")
    public boolean isValid;
    public ArrayList<Object> validationErrors;
    public String id;
}

@Data
class CustomPeriodSeries {
    public Date x;
    public double y;
}
