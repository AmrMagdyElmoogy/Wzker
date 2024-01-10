package com.example.wzker.util

val ids = listOf(
    "Notification_1",
    "Notification_2",
    "Notification_3",
    "Notification_4",
    "Notification_5",
)


val quranSurahsArabicWithAyatCount: Map<Int, Pair<String, Int>> = mapOf(
    1 to Pair("سورة الفاتحة", 7),
    2 to Pair("سورة البقرة", 286),
    3 to Pair("سورة آل عمران", 200),
    4 to Pair("سورة النساء", 176),
    5 to Pair("سورة المائدة", 120),
    6 to Pair("سورة الأنعام", 165),
    7 to Pair("سورة الأعراف", 206),
    8 to Pair("سورة الأنفال", 75),
    9 to Pair("سورة التوبة", 129),
    10 to Pair("سورة يونس", 109),
    11 to Pair("سورة هود", 123),
    12 to Pair("سورة يوسف", 111),
    13 to Pair("سورة الرعد", 43),
    14 to Pair("سورة إبراهيم", 52),
    15 to Pair("سورة الحجر", 99),
    16 to Pair("سورة النحل", 128),
    17 to Pair("سورة الإسراء", 111),
    18 to Pair("سورة الكهف", 110),
    19 to Pair("سورة مريم", 59),
    20 to Pair("سورة طه", 130),
    21 to Pair("سورة الأنبياء", 112),
    22 to Pair("سورة الحج", 78),
    23 to Pair("سورة المؤمنون", 118),
    24 to Pair("سورة النور", 64),
    25 to Pair("سورة الفرقان", 77),
    26 to Pair("سورة الشعراء", 227),
    27 to Pair("سورة النمل", 93),
    28 to Pair("سورة القصص", 88),
    29 to Pair("سورة العنكبوت", 69),
    30 to Pair("سورة الروم", 60),
    31 to Pair("سورة لقمان", 34),
    32 to Pair("سورة السجدة", 30),
    33 to Pair("سورة الأحزاب", 73),
    34 to Pair("سورة سبإ", 54),
    35 to Pair("سورة فاطر", 45),
    36 to Pair("سورة يس", 83),
    37 to Pair("سورة الصافات", 182),
    38 to Pair("سورة ص", 88),
    39 to Pair("سورة الزمر", 75),
    40 to Pair("سورة غافر", 85),
    41 to Pair("سورة فصلت", 54),
    42 to Pair("سورة الشورى", 53),
    43 to Pair("سورة الزخرف", 89),
    44 to Pair("سورة الدخان", 59),
    45 to Pair("سورة الجاثية", 37),
    46 to Pair("سورة الأحقاف", 35),
    47 to Pair("سورة محمد", 38),
    48 to Pair("سورة الفتح", 29),
    49 to Pair("سورة الحشر", 18),
    50 to Pair("سورة ق", 45),
    51 to Pair("سورة الذاريات", 60),
    52 to Pair("سورة الطور", 49),
    53 to Pair("سورة النجم", 62),
    54 to Pair("سورة القمر", 55),
    55 to Pair("سورة الرحمن", 78),
    56 to Pair("سورة الواقعة", 96),
    57 to Pair("سورة الحديد", 29),
    58 to Pair("سورة المجادلة", 22),
    59 to Pair("سورة الحشر", 24),
    60 to Pair("سورة الممتحنة", 13),
    61 to Pair("سورة الصف", 14),
    62 to Pair("سورة الجمعة", 11),
    63 to Pair("سورة المنافقون", 11),
    64 to Pair("سورة التغابن", 18),
    65 to Pair("سورة الطلاق", 12),
    66 to Pair("سورة التحريم", 12),
    67 to Pair("سورة الملك", 30),
    68 to Pair("سورة القلم", 52),
    69 to Pair("سورة الحاقة", 52),
    70 to Pair("سورة المعارج", 44),
    71 to Pair("سورة نوح", 28),
    72 to Pair("سورة الجن", 28),
    73 to Pair("سورة المزمل", 20),
    74 to Pair("سورة المدثر", 56),
    75 to Pair("سورة القيامة", 40),
    76 to Pair("سورة الإنسان", 31),
    77 to Pair("سورة المرسلات", 50),
    78 to Pair("سورة النبأ", 40),
    79 to Pair("سورة النازعات", 46),
    80 to Pair("سورة عبس", 42),
    81 to Pair("سورة التكوير", 29),
    82 to Pair("سورة الإنفطار", 19),
    83 to Pair("سورة المطففين", 36),
    84 to Pair("سورة الإنشقاق", 25),
    85 to Pair("سورة البروج", 22),
    86 to Pair("سورة الطارق", 17),
    87 to Pair("سورة الأعلى", 19),
    88 to Pair("سورة الغاشية", 26),
    89 to Pair("سورة الفجر", 30),
    90 to Pair("سورة البلد", 20),
    91 to Pair("سورة الشمس", 15),
    92 to Pair("سورة الليل", 21),
    93 to Pair("سورة الضحى", 11),
    94 to Pair("سورة الشرح", 8),
    95 to Pair("سورة التين", 8),
    96 to Pair("سورة العلق", 19),
    97 to Pair("سورة القدر", 5),
    98 to Pair("سورة البينة", 8),
    99 to Pair("سورة الزلزلة", 8),
    100 to Pair("سورة العاديات", 11),
    101 to Pair("سورة القارعة", 11),
    102 to Pair("سورة التكاثر", 8),
    103 to Pair("سورة العصر", 3),
    104 to Pair("سورة الهمزة", 9),
    105 to Pair("سورة الفيل", 5),
    106 to Pair("سورة قريش", 4),
    107 to Pair("سورة الماعون", 7),
    108 to Pair("سورة الكوثر", 3),
    109 to Pair("سورة الكافرون", 6),
    110 to Pair("سورة النصر", 3),
    111 to Pair("سورة المسد", 5),
    112 to Pair("سورة الإخلاص", 4),
    113 to Pair("سورة الفلق", 5),
    114 to Pair("سورة الناس", 6)
)

