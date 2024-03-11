package com.example.app.data

import com.example.app.R
import java.util.Random

/// TODO: Hilt Injection?
object PlaceRepository {
    private val random = Random()
    private val places = mapOf(
        "Cape May Beaches" to Place(
            "Cape May Beaches",
            "Visit these beautiful and expansive pearly white beaches with mouth-dropping views. Across the various beaches, vacationers can swim, skimboard, surf, fish, kayak and play volleyball in designated areas. Lifeguards are on duty and many enjoy swimming in the cool waters during summer. At sunset or sunrise, the beaches and waves provide a majestic scenery complement with dolphin sightings.",
            R.drawable.capemay_beach,
            Address(251, "Beach Ave", "Cape May City"),
            setOf(PlaceType.Fun),
            url = "https://www.capemay.com/cape-may-beaches",
            price = "Prices vary by season"
        ),
        "Washington Street Mall" to Place(
            "Washington Street Mall",
            "The Washington Street Mall is one-stop shop for all your retail. Ranging from book stores, home goods stores, clothing stores and much more. There is beautiful buildings and pathways to explore, along with many restaurants and sweets being sold",
            R.drawable.washington_mall,
            Address(401, "Washington St", "Cape May City"),
            setOf(PlaceType.Shopping, PlaceType.Food),
            url = "http://www.heartofcapemay.com/",
            time = "Hours vary seasonally",
            price = "Free to enter, stores have individual prices"
        ),
        "Jersey Shore Alpacas" to Place(
            "Jersey Shore Alpacas",
            "Interact with gentle and friendly alpacas at this calming and whimsical farm. This farm is home to 20 fluffy alpacas where patrons can interact with them and even purchase sweaters, gloves, and socks made from alpaca fleece.",
            R.drawable.alpaca,
            Address(521, "South Route 47", "Cape May City"),
            setOf(PlaceType.Zoo, PlaceType.Shopping),
            url = "https://www.jerseyshorealpacas.com/",
            time = "Hours vary seasonally",
            price = "Free to enter, souvenirs have separate prices"
        ),
        "Cape May Lighthouse" to Place(
            "Cape May Lighthouse",
            "Built in 1859 and opened to the public in 1988, the Cape May Lighthouse has welcomed more than 2.5 million visitors to date. Travelers who scale the 199 steps to the top of the lighthouse will be rewarded with panoramic oceanfront views. Those who would rather stay on solid ground can enjoy the Oil House, which contains a visitor center and a museum shop with souvenirs.",
            R.drawable.lighthouse,
            Address(215, "Lighthouse Ave", "Cape May City"),
            setOf(PlaceType.Landmark),
            url = "https://capemaymac.org/experience/cape-may-lighthouse/",
            time = "9 a.m.-5 p.m. daily",
            price = "\$10 for adults; \$5 for kids 3-12"
        ),
        "Emlen Physick Estate" to Place(
            "Emlen Physick Estate",
            "Take a step back in time at the Emlen Physick Estate, the only Victorian-era house museum in New Jersey. Completed in 1879, the house belonged to a wealthy Philadelphia family, the Physicks. Take a step back in time at the Emlen Physick Estate, the only Victorian-era house museum in New Jersey. Completed in 1879, the house belonged to a wealthy Philadelphia family, the Physicks.",
            R.drawable.mansion,
            Address(1048, "Washington St", "Cape May City"),
            setOf(PlaceType.Landmark),
            url = "https://capemaymac.org/experience/emlen-physick-estate/",
            price = "\$15 for adults; \$8-\$10 for kids 3-12",
            time = "9 a.m.-8:30 p.m. daily"
        ),
        "Naval Air Station Wildwood Aviation Museum" to Place(
            "Naval Air Station Wildwood Aviation Museum",
            "The Naval Air Station (NAS) Wildwood Aviation Museum is located inside a former World War II hangar that dates back to 1943. It was originally used as a training facility for dive bombers to practice takeoffs, landings, night flying and target practice. After the war, the hanger was used for incoming and outgoing charter and commuter flights until about 1990, when it began falling into disrepair. Today, the NAS Wildwood Aviation Museum boasts a variety of historic planes, helicopters and WWII memorabilia. Travelers can view medevac helicopters, a large collection of military jets and more than 20 propeller aircraft, in addition to other automobiles and 1940s artifacts like retro soda machines. ",
            R.drawable.aviation,
            Address(500, "Forrestal Road", "Cape May City"),
            setOf(PlaceType.Museum),
            url = "https://usnasw.org/",
            price = "\$14 for adults; \$10 for kids 3-12",
            time = "Hours vary seasonally"
        ),
        "World War II Lookout Tower" to Place(
            "World War II Lookout Tower",
            "Part of Cape May Point State Park, the World War II Lookout Tower – officially known as Fire Control Tower No. 23 – was built in 1942 and is New Jersey's last restorable tower from World War II. Opened to the public in 2009, the tower played a major role in defending the coast during the war and is part of the Harbor Defense of the Delaware system (known as Fort Miles), which stretched from North Wildwood, New Jersey, to Bethany Beach, Delaware. Today, travelers can climb six flights of stairs to the top of the structure and learn about the historical significance of the area during WWII – as well as how the tower was used to spot enemy ships",
            R.drawable.tower,
            Address(536, "Sunset Blvd", "Cape May City"),
            setOf(PlaceType.Landmark),
            url = "https://capemaymac.org/experience/world-war-ii-tower/",
            time = "10 a.m.-4 p.m. daily",
            price = "\$6 for adults; \$3 for kids 3-12"
        ),
    )

    fun get(name: String): Place {
        return places[name]!!
    }

    fun filterByCity(city: City): List<Place> {
        return places
            .filter { it.value.address.city == city.name }
            .values
            .toList()
    }

    fun filterByType(types: Set<PlaceType>) : List<Place> {
        if (types.isEmpty()) {
            return allPlaces()
        }
        return allPlaces().filter { types.intersect(it.types).isNotEmpty() }
    }

    fun getRandom() : Place {
        return places.values.elementAt(random.nextInt(places.size))
    }

    fun allPlaces(): List<Place> {
        return places.values.toList()
    }
}