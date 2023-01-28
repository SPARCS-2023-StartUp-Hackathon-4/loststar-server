package sparcs.loststar.common

import sparcs.loststar.domain.LostFoundRequest
import sparcs.loststar.domain.LostFoundType

fun getLostFoundDummyFormList() = mutableListOf(
    LostFoundRequest(
        title = "아이폰12",
        category = Category.휴대폰,
        location = Location.강남.name,
        locationDetail = "강남역 7번출구 부근",
        date = "2023.01.28",
        time = "22시경",
        image = "https://loststar44.s3.ap-northeast-2.amazonaws.com/image1003a479-ffd3-42b9-9727-cdab882591b8-s_phone.jpg",
        description = "술먹고 집 들어가다가 정신차려보니 잃어버렸습니다 ㅠㅠ 느낌상 강남역에서 잃어버린 것 같아요. 찾아주신 분께 별조각 200개 사례합니다...!! 바꾼지 얼마 안 되어서요 ㅠㅠㅠㅠ",
        reward = 200,
        boost = false,
        type = LostFoundType.LOST,
    ),
    LostFoundRequest(
        title = "에어팟 3",
        category = Category.전자기기,
        location = Location.강남.name,
        locationDetail = "더이스케이프 강남역점",
        date = "2023.01.13",
        time = "14시경",
        image = "https://loststar44.s3.ap-northeast-2.amazonaws.com/imagec394c9c9-bd63-4766-854b-f242f2e7c2fc-airpod1.jpg",
        description = "노원공원에서 에어팟 3 주웠습니당~ 근처 파출소에 맡겨놨으니 찾아가세용 :)",
        reward = 0,
        boost = false,
        type = LostFoundType.FOUND,
    ),
    LostFoundRequest(
        title = "파란색 가죽 카드지갑",
        category = Category.지갑,
        location = Location.강남.name,
        locationDetail = "강남역 투썸플레이스",
        date = "2023.01.20",
        time = "18시경",
        image = "https://loststar44.s3.ap-northeast-2.amazonaws.com/image5bb513ce-f26d-4ed9-885f-3380aaf4d045-s_wallet.jpg",
        description = "남자친구한테 생일선물로 받은 카드지갑을 잃어버렸습니다 ㅜㅜ 찾아주시면 별 조각 왕 많이 드릴게용!!! (남친이 알기 전에 찾아주세요 ㅜㅜㅜ)",
        reward = 1000,
        boost = true,
        type = LostFoundType.LOST,
    ),
    LostFoundRequest(
        title = "우리카드",
        category = Category.카드,
        location = Location.강남.name,
        locationDetail = "강남역 코다차야 술집",
        date = "2023.02.03",
        time = "15시경",
        image = "https://loststar44.s3.ap-northeast-2.amazonaws.com/imagef47965bd-7a13-468a-b75d-50a1f8c84bbd-s_card.jpg",
        description = "한정 발급가능한 우리카드 주웠습니다! 이거 이제 재발급 안 되는걸로 아는데,,, 주인분 언넝 찾아가세요~~ 근처 경찰서에 맡겨놨습니다 ㅎㅎ",
        reward = 0,
        boost = true,
        type = LostFoundType.FOUND,
    )
)