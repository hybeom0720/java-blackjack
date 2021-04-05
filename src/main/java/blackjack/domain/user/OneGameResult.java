package blackjack.domain.user;

public enum OneGameResult {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String gameKoreanResult;

    OneGameResult(String gameKoreanResult) {
        this.gameKoreanResult = gameKoreanResult;
    }

    public static OneGameResult getGameExpressResult(double profitRate) {
        if (profitRate < 0) {
            return OneGameResult.LOSE;
        }
        if (profitRate > 0) {
            return OneGameResult.WIN;
        }
        return OneGameResult.TIE;
    }

    public String getResult() {
        return this.gameKoreanResult;
    }
}
