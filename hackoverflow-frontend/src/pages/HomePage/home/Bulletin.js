import React from "react";
import {
  Flex,
  chakra,
  Box,
  Link,
  Image,
  VStack,
  HStack,
  Heading,
  Divider,
  Text,
} from "@chakra-ui/react";

// fetch("https://hackoverflow-ms-gateway.herokuapp.com/get-all-article/", {
//   method: "post",
//   headers: {
//     Authtoken: "2cde336f-94c2-4393-9152-e29462e78406",
//   },
// })
//   .then((response) => response.json())
//   .then((data) => console.log(data))
//   .catch((err) => console.log(err));

let articleArr = [
  {
    date: "AUG 10, 2022",
    category: "housing",
    title: "Condo resale prices rise for 24th straight month in July",
    text: "SINGAPORE - Prices of resale condominium units edged up for the 24th straight month in July, while transactions dipped amid rising interest rates." ,
    url: "https://www.straitstimes.com/business/property/condo-resale-prices-rise-for-24th-straight-month-in-july-volume-falls-307-year-on-year",
  },
  {
    date: "July 19, 2022",
    category: "housing",
    title: "Singapore Central Bank Sees Stable Residential Property Market",
    text: "Singapore’s residential property market is on a sustainable path and is stable compared with other cities, according to the nation’s central bank." ,
    url: "https://www.bloomberg.com/news/articles/2022-07-19/singapore-central-bank-sees-stable-residential-property-market",
  },
  {
    date: "04 Aug 2022",
    category: "Car",
    title: "How the COE system went from managing rapid vehicle growth to hitting record premiums",
    text: "From August, bidding for a Certificate of Entitlement (COE) – which gives a person the right to own and use a vehicle in Singapore – will be based on a rolling average of vehicle deregistrations over two quarters instead of one.",
    url: "https://www.channelnewsasia.com/singapore/coe-quota-policy-cars-vehicles-history-record-prices-2843996",
  },
  {
    date: "AUG 3, 2022",
    category: "SINGAPORE - Certificate of entitlement (COE) premiums for large cars and the Open category ended lower at the latest tender exercise that closed on Wednesday (Aug 3).",
    title: "COE premiums for large cars and Open category dip",
    text: "",
    url: "https://www.straitstimes.com/singapore/transport/coe-premiums-for-large-cars-and-open-category-dipped-slightly",
  },
  {
    date: "AUG 8, 2022",
    category: "economy",
    title: "S'poreans must brace themselves for a less peaceful region, period of high inflation: PM Lee",
    text: "SINGAPORE - Singaporeans must brace themselves and be psychologically prepared that in the next decades, the region might not be as peaceful and stable as it has been, Prime Minister Lee Hsien Loong said on Monday (Aug 8).",
    url: "https://www.straitstimes.com/singapore/politics/brace-for-a-less-peaceful-region-period-of-high-inflation-pm-lee",
  },
];

const article = (item) => {
  return (
    <Flex>
      <Flex p={5} w="full" alignItems="center" justifyContent="center">
        <Box
          mx="auto"
          px={8}
          py={4}
          rounded="lg"
          shadow="lg"
          bg="white"
          _dark={{
            bg: "gray.800",
          }}
          maxW="2xl"
        >
          <Flex justifyContent="space-between" alignItems="center">
            <chakra.span
              fontSize="sm"
              color="gray.800"
              _dark={{
                color: "gray.400",
              }}
            >
              {item.date}
            </chakra.span>
            <Link
              px={3}
              py={1}
              bg="teal.600"
              color="white"
              fontSize="sm"
              fontWeight="700"
              rounded="md"
              _hover={{
                bg: "gray.500",
              }}
            >
              {item.category}
            </Link>
          </Flex>

          <Box mt={2}>
            <Link
              fontSize="2xl"
              color="gray.700"
              _dark={{
                color: "white",
              }}
              fontWeight="700"
              _hover={{
                color: "gray.600",
                _dark: {
                  color: "gray.200",
                },
                textDecor: "underline",
              }}
              href={item.url}
              target={"_blank"}
            >
              {item.title}
            </Link>
            <HStack>
              <Image
                mx={4}
                w={50}
                h={50}
                src="https://en.meming.world/images/en/6/6e/Surprised_Pikachu.jpg"
              />

              <chakra.p
                mt={2}
                color="black"
                _dark={{
                  color: "gray.300",
                }}
                max-lines={3}
                overflow={"hidden"}
              >
                <Text just>{item.text}</Text>
              </chakra.p>
            </HStack>
          </Box>
          <Divider pt="5px" borderColor="gray.400" />
          <Flex justifyContent="space-between" alignItems="center" mt={2}>
            <Link
              color="brand.600"
              _dark={{
                color: "brand.400",
              }}
              _hover={{
                textDecor: "underline",
              }}
              href={item.url}
              target={"_blank"}
            >
              Read more
            </Link>
          </Flex>
        </Box>
      </Flex>
    </Flex>
  );
};

export const Bulletin = () => {
  return (
    <VStack height={"113vh"} overflow="auto">
      <Heading pt="1rem" color="white">
        Bulletin Board
      </Heading>
      {articleArr.map((item) => article(item))}
    </VStack>
  );
};
