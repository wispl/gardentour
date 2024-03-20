package com.example.app.data.datasource

import com.example.app.R
import com.example.app.data.model.*
import java.time.LocalTime

class PlacesDatasource {
    val places = mapOf(
        "Cape May Beaches" to Place(
            "Cape May Beaches",
            "Visit these beautiful and expansive pearly white beaches with mouth-dropping views. Across the various beaches, vacationers can swim, skimboard, surf, fish, kayak and play volleyball in designated areas. Lifeguards are on duty and many enjoy swimming in the cool waters during summer. At sunset or sunrise, the beaches and waves provide a majestic scenery complement with dolphin sightings.",
            R.drawable.capemay_beach,
            "251 Beach Ave",
            "Cape May City",
            setOf(PlaceType.Fun),
            url = "https://www.capemay.com/cape-may-beaches",
            price = Price.Vary.toString()
        ),
        "Washington Street Mall" to Place(
            "Washington Street Mall",
            "The Washington Street Mall is one-stop shop for all your retail. Ranging from book stores, home goods stores, clothing stores and much more. There is beautiful buildings and pathways to explore, along with many restaurants and sweets being sold",
            R.drawable.washington_mall,
            "401 Washington St",
            "Cape May City",
            setOf(PlaceType.Shopping, PlaceType.Food),
            url = "http://www.heartofcapemay.com/",
            time = Hours.Vary,
        ),
        "Jersey Shore Alpacas" to Place(
            "Jersey Shore Alpacas",
            "Interact with gentle and friendly alpacas at this calming and whimsical farm. This farm is home to 20 fluffy alpacas where patrons can interact with them and even purchase sweaters, gloves, and socks made from alpaca fleece.",
            R.drawable.alpaca,
            "521 South Route 47",
            "Cape May City",
            setOf(PlaceType.Zoo, PlaceType.Shopping),
            url = "https://www.jerseyshorealpacas.com/",
            time = Hours.Vary,
        ),
        "Cape May Lighthouse" to Place(
            "Cape May Lighthouse",
            "Built in 1859 and opened to the public in 1988, the Cape May Lighthouse has welcomed more than 2.5 million visitors to date. Travelers who scale the 199 steps to the top of the lighthouse will be rewarded with panoramic oceanfront views. Those who would rather stay on solid ground can enjoy the Oil House, which contains a visitor center and a museum shop with souvenirs.",
            R.drawable.lighthouse,
            "215 Lighthouse Ave",
            "Cape May City",
            setOf(PlaceType.Landmark),
            url = "https://capemaymac.org/experience/cape-may-lighthouse/",
            time = Hours.Range(LocalTime.of(9,0), LocalTime.of(17, 0)),
            price = Price.Cost(5, 10).toString()
        ),
        "Emlen Physick Estate" to Place(
            "Emlen Physick Estate",
            "Take a step back in time at the Emlen Physick Estate, the only Victorian-era house museum in New Jersey. Completed in 1879, the house belonged to a wealthy Philadelphia family, the Physicks. Take a step back in time at the Emlen Physick Estate, the only Victorian-era house museum in New Jersey. Completed in 1879, the house belonged to a wealthy Philadelphia family, the Physicks.",
            R.drawable.mansion,
            "1048 Washington St",
            "Cape May City",
            setOf(PlaceType.Landmark),
            url = "https://capemaymac.org/experience/emlen-physick-estate/",
            price = Price.Cost("Adult: $8-10     Children: $15").toString(),
            time = Hours.Range(LocalTime.of(9,0), LocalTime.of(20, 30))
        ),
        "Naval Air Station Wildwood Aviation Museum" to Place(
            "Naval Air Station Wildwood Aviation Museum",
            "The Naval Air Station (NAS) Wildwood Aviation Museum is located inside a former World War II hangar that dates back to 1943. It was originally used as a training facility for dive bombers to practice takeoffs, landings, night flying and target practice. After the war, the hanger was used for incoming and outgoing charter and commuter flights until about 1990, when it began falling into disrepair. Today, the NAS Wildwood Aviation Museum boasts a variety of historic planes, helicopters and WWII memorabilia. Travelers can view medevac helicopters, a large collection of military jets and more than 20 propeller aircraft, in addition to other automobiles and 1940s artifacts like retro soda machines. ",
            R.drawable.aviation,
            "500 Forrestal Road",
            "Cape May City",
            setOf(PlaceType.Museum),
            url = "https://usnasw.org/",
            price = Price.Cost(10, 14).toString(),
            time = Hours.Vary
        ),
        "World War II Lookout Tower" to Place(
            "World War II Lookout Tower",
            "Part of Cape May Point State Park, the World War II Lookout Tower – officially known as Fire Control Tower No. 23 – was built in 1942 and is New Jersey's last restorable tower from World War II. Opened to the public in 2009, the tower played a major role in defending the coast during the war and is part of the Harbor Defense of the Delaware system (known as Fort Miles), which stretched from North Wildwood, New Jersey, to Bethany Beach, Delaware. Today, travelers can climb six flights of stairs to the top of the structure and learn about the historical significance of the area during WWII – as well as how the tower was used to spot enemy ships",
            R.drawable.tower,
            "536 Sunset Blvd",
            "Cape May City",
            setOf(PlaceType.Landmark),
            url = "https://capemaymac.org/experience/world-war-ii-tower/",
            time = Hours.Range(LocalTime.of(10,0), LocalTime.of(16, 0)),
            price = Price.Cost(3, 6).toString(),
        ),
        "Cape May Wineries and Vineyards" to Place(
            "Cape May Wineries and Vineyards",
            "New Jersey is home to more than 50 wineries, and some of the biggest producers can be found in Cape May. Fan favorites include the Cape May Winery and Vineyard and the Willow Creek Winery, but oenophiles should check out all options before picking a spot (though there's no harm in hopping between a few).",
            R.drawable.wineries,
            "711 Town Bank Road",
            "Cape May City",
            setOf(PlaceType.Fun, PlaceType.Shopping),
            url = "https://capemaywinery.com/",
            time = Hours.Vary,
            price = Price.Vary.toString()
        ),
        "Cape May Wineries and Vineyards" to Place(
            "Cape May Wineries and Vineyards",
            "New Jersey is home to more than 50 wineries, and some of the biggest producers can be found in Cape May. Fan favorites include the Cape May Winery and Vineyard and the Willow Creek Winery, but oenophiles should check out all options before picking a spot (though there's no harm in hopping between a few).",
            R.drawable.wineries,
            "711 Town Bank Road",
            "Cape May City",
            setOf(PlaceType.Fun, PlaceType.Shopping),
            url = "https://capemaywinery.com/",
            time = Hours.Vary,
            price = Price.Vary.toString()
        ),
        "Congress Hall" to Place(
            "Congress Hall",
            "Along the shoreline of New Jersey's Cape May, you'll find Congress Hall, a historic hotel with boarding house origins dating back to 1816. Today, Congress Hall pays tribute to its past by maintaining much of its Victorian charm – a particular draw for recent guests. The Brown Room, with its Prohibition-era lounge setting, offers nightly entertainment and friendly bartenders that remind visitors of a bygone era. ",
            R.drawable.congress_hall,
            "200 Congress Place",
            "Cape May City",
            setOf(PlaceType.Hotel),
            url = "https://www.caperesorts.com/congress-hall",
            price = Price.Cost(189).toString()
        ),
        "Virginia Hotel & Cottages" to Place(
            "The Virginia Hotel & Cottages",
            "Overlooking the New Jersey shoreline from the state's southernmost point, The Virginia Hotel & Cottages in Cape May offers guests a classier alternative to the area's kitschier digs. Guests praise the hotel's efforts to make your trip stress-free, which includes providing complimentary beach chairs, towels and umbrellas for those looking to soak up the sun on the property's private stretch of sand.",
            R.drawable.virginia_hotel,
            "25 Jackson St",
            "Cape May City",
            setOf(PlaceType.Hotel),
            url = "https://www.caperesorts.com/virginia-hotel",
            price = Price.Cost(218).toString()
        ),

//        https://travel.usnews.com/dims4/USNEWS/b306baa/2147483647/resize/976x652%5E%3E/crop/976x652/quality/85/?url=https%3A%2F%2Ftravel.usnews.com%2Fimages%2FGettyImages-1090124064.jpg
        "Ocean City Boardwalk" to Place(
            "Ocean City BoardWalk",
            "The Ocean City Boardwalk serves as the area's primary pedestrian artery and runs about 2 1/2 miles along the beach. Ocean City's original boardwalk, along with much of the city, burnt down in 1927. The current boardwalk was built immediately after, but sections have been repaired and replaced continuously over time. Currently, the boardwalk hosts amusement park rides, restaurants, stores and more. The Ocean City Boardwalk remains an iconic path to walk, run or bike down.",
            R.drawable.boardwalk,
            "400 Boardwalk",
            "Ocean City",
            setOf(PlaceType.Fun),
            url = "https://oceancityvacation.com/where-to-shop/boardwalk.html",
        ),

        "Ocean City Beach" to Place(
            "Ocean City Beach",
            "The barrier island of Ocean City advertises 8 miles of coast for visitors to explore. The beach's warm weather and strict rules primarily draw visitors looking for a relatively calm and relaxing opportunity to enjoy the saltwater and sun.",
            // https://travel.usnews.com/dims4/USNEWS/d0001f8/2147483647/resize/976x652%5E%3E/crop/976x652/quality/85/?url=https%3A%2F%2Ftravel.usnews.com%2Fimages%2FGettyImages-1077660992.jpg
            R.drawable.ocean_beach,
            "Ocean City",
            "Ocean City",
            setOf(PlaceType.Fun),
            url = "http://www.ocnj.us/beach/",
            price = Price.Cost(5, 10, 25).toString()
        ),

        "Corson's Inlet State Park" to Place(
            "Corson's Inlet State Park",
            "A small strip of undeveloped land, Corson's Inlet State Park was established in 1969 to protect its distinct wildlife. This state park appeals primarily to nature lovers, who want a more organic beach experience than the popular city beach. The park boasts hiking trails that weave through sand dunes and beaches for sun-soaking (but no swimming) and other wilderness areas. There is also a boat launch available for anyone who wants to take advantage of a day on the water.",
            // https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Corson%E2%80%99s_Inlet_and_Russ_Chatin_Bridge.jpg/1280px-Corson%E2%80%99s_Inlet_and_Russ_Chatin_Bridge.jpg
            R.drawable.ocean_beach,
            "County Hwy 619",
            "Ocean City",
            setOf(PlaceType.Fun),
            "https://www.state.nj.us/dep/parksandforests/parks/corsons.html",
        ),

        "Playland's Castaway Cove" to Place(
            "Playland's Castaway Cove",
            "More than 32 rides, including a Ferris wheel, make Playland's Castaway Cove stand out on the Ocean City Boardwalk. The amusement park has been operating for more than 60 years and has developed a reputation for its assortment of thrill-seeking and family-friendly rides. In addition to its numerous rides, Playland's Castaway Cove also operates an indoor arcade on-site as well as two mini-golf locations and a go-kart speedway nearby.",
            R.drawable.ocean_beach,
            // https://upload.wikimedia.org/wikipedia/commons/thumb/7/72/Playland_Castaway_Cove-21_%287595002752%29.jpg/640px-Playland_Castaway_Cove-21_%287595002752%29.jpg
            // Kevin Jarrett
            "1020 Boardwalk",
            "Ocean City",
            setOf(PlaceType.Fun),
            "https://oceancityfun.com/",
            price = Price.Vary.toString(),
            time = Hours.Vary
        ),

        "Gillian's Wonderland Pier" to Place(
            "Gillian's Wonderland Pier",
            "An initial iteration of the park opened in 1930 following a fire on the boardwalk, and its primary attractions were a Ferris wheel and a merry-go-round. The park has since moved and expanded to include more than 25 rides, which travelers can ride for a variety of ticket prices. Gillian's Wonderland Pier's attractions skew toward younger children, but there are rides to amuse most ages.",
            R.drawable.ocean_beach,
            "600 Boardwalk",
            "Ocean City",
            setOf(PlaceType.Fun),
            "http://www.gillians.com/",
            price = Price.Vary.toString(),
            time = Hours.Vary
        ),

        "Glazed Over Studios" to Place(
            "Glazed Over Studios",
            "Glazed Over Studios offers opportunities to try out pottery, mosaics and canvas walk-in sessions, making it an excellent, impromptu option for Ocean City tourists. Travelers who prefer a more structured learning opportunity can sign up for one of the studio's classes, which are geared toward adults or kids. ",
//          https://media-cdn.tripadvisor.com/media/photo-s/1a/71/2d/04/glazed-over-studio-on.jpg
//          JCN
            R.drawable.ocean_beach,
            "704 Asbury Ave",
            "Ocean City",
            setOf(PlaceType.Fun),
            "https://www.glazedoverstudios.com/",
            price = Price.Vary.toString(),
            // 11 a.m.-5 p.m. Thursday to Monday
            time = Hours.Vary
        ),

        "Stainton's" to Place(
            "Stainton's",
            "Stainton's Gallery of Shops provides space for more than 70 unique vendors to sell their wares. While it's best to shop at Stainton's without expectations, the wares sold can include jewelry, candles, art, pottery, toys and apparel. Past travelers say that Stainton's provides an excellent place to pick up a one-of-a-kind gift or a memorable keepsake.",
            // https://travel.usnews.com/dims4/USNEWS/d1bf4ba/2147483647/resize/976x652%5E%3E/crop/976x652/quality/85/?url=https%3A%2F%2Ftravel.usnews.com%2Fimages%2Fstaintons-exterior.jpg
            R.drawable.ocean_beach,
            "810 Asbury Ave",
            "Ocean City",
            setOf(PlaceType.Shopping),
            "https://staintons.com/",
            time = Hours.Vary
        )
    )
}