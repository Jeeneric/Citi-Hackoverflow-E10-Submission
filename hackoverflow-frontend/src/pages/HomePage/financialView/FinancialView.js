import { React, useState, useEffect } from "react";
import {
  Flex,
  Box,
  CircularProgress,
  CircularProgressLabel,
  Grid,
  GridItem,
  Heading,
  Stack,
  Text,
} from "@chakra-ui/react";
import AddCompartment from "./AddCompartment";
import TransferForm from "./TransferForm";
import { NavBar } from "../../../container";

const FinancialView = () => {
  const [savings, setSavings] = useState(12055);
  const [togForm, settogForm] = useState(false);
  const [comps, setComps] = useState([
    {
      account: {
        accountId: "5e88aba6-b381-4b0f-bedb-8606e357ac3d",
        userId: "414afd18-0477-4a3e-87ba-b85440485c44",
        balance: 0,
        accountType: "DESTINATION",
      },
      destination: {
        accountId: "5e88aba6-b381-4b0f-bedb-8606e357ac3d",
        name: "HDB",
        description: "this is some goal i made for myself",
        goal: 100000.0,
        category: "CUSTOM",
      },
    },
    {
      account: {
        accountId: "09276d3d-6441-49f1-9105-8c62e90bb438",
        userId: "414afd18-0477-4a3e-87ba-b85440485c44",
        balance: 12,
        accountType: "DESTINATION",
      },
      destination: {
        accountId: "09276d3d-6441-49f1-9105-8c62e90bb438",
        name: "Honey Moon",
        description: "this is some goal i made for myself",
        goal: 100000,
        category: "CUSTOM",
      },
    },
    {
      account: {
        accountId: "9a6db7f6-45dc-4dd4-b296-a8ff9698fab8",
        userId: "414afd18-0477-4a3e-87ba-b85440485c44",
        balance: 20,
        accountType: "DESTINATION",
      },
      destination: {
        accountId: "9a6db7f6-45dc-4dd4-b296-a8ff9698fab8",
        name: "Education",
        description: "tyjuy",
        goal: 100,
        category: "CAR",
      },
    },
    {
      account: {
        accountId: "7377af00-c8ee-4a19-8d7a-d72347653587",
        userId: "414afd18-0477-4a3e-87ba-b85440485c44",
        balance: 5,
        accountType: "DESTINATION",
      },
      destination: {
        accountId: "7377af00-c8ee-4a19-8d7a-d72347653587",
        name: "Concert Ticket",
        description: "gkh",
        goal: 100,
        category: "CAR",
      },
    },
    {
      account: {
        accountId: "4e9ac4c1-56a5-487f-ad1d-3132fe840baa",
        userId: "414afd18-0477-4a3e-87ba-b85440485c44",
        balance: 0,
        accountType: "DESTINATION",
      },
      destination: {
        accountId: "4e9ac4c1-56a5-487f-ad1d-3132fe840baa",
        name: "Car",
        description: "pls",
        goal: 69,
        category: "CAR",
      },
    },
    {
      account: {
        accountId: "4e9ac4c1-56a5-487f-ad1d-3132fe840baa",
        userId: "414afd18-0477-4a3e-87ba-b85440485c44",
        balance: 50,
        accountType: "DESTINATION",
      },
      destination: {
        accountId: "4e9ac4c1-56a5-487f-ad1d-3132fe840baa",
        name: "Instrument",
        description: "pls",
        goal: 100,
        category: "CAR",
      },
    },
  ]);
  const [transfer, setTransfer] = useState([]);

  const addDest = async (dest) => {
    const res = await fetch(
      `https://hackoverflow-ms-gateway.herokuapp.com/destination/`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authtoken: "0688e599-d083-4fc9-9523-9eba9b05034b",
        },
        body: JSON.stringify(dest),
      }
    ).catch((err) => console.log(err));
  };
  const Transfer = async (trf) => {
    const res = await fetch(
      `https://hackoverflow-ms-gateway.herokuapp.com/transaction/transfer/`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authtoken: "0688e599-d083-4fc9-9523-9eba9b05034b",
        },
        body: JSON.stringify(trf),
      }
    ).catch((err) => console.log(err));
  };

  // useEffect(() => {
  //   fetch("https://hackoverflow-ms-gateway.herokuapp.com/dashboard/", {
  //     headers: { Authtoken: "0688e599-d083-4fc9-9523-9eba9b05034b" },
  //   })
  //     .then((res) => res.json())
  //     .then((result) => setComps(result.dashboard))
  //     .catch((err) => console.log(err));
  // }, []);

  // const addComp = async (comp) => {
  //   setComps([...comps, comp]);
  // };

  // const Transfer = async (trf) => {
  //   setTransfer([...transfer, trf]);
  //   console.log(transfer)
  // };
  console.log(typeof comps);
  console.log(comps);
  return (
    <>
      <NavBar />
      <Flex
        position="relative"
        flexDirection="column"
        width="100%"
        height="100%"
        justifyContent="center"
      >
        <Stack
          direction="row"
          alignItems="center"
          justifyContent="center"
          pt="1rem"
        >
          <Heading size="xl" ml="20px" mb="10px">
            Financial View
          </Heading>
        </Stack>

        <Stack
          direction="row"
          alignItems="center"
          justifyContent="center"
          pt="1rem"
        >
          <Box
            bg="gray.200"
            shadow="md"
            width="400px"
            height="100px"
            borderRadius="5px"
            mb="2rem"
          >
            <Grid templateColumns="repeat(5,1fr)" templateRows="repeat(4,1fr)">
              <GridItem colStart={2} colSpan={3} pl="20%" pt="2rem">
                <Heading size="sm">Main Savings Account:</Heading>
              </GridItem>
              <GridItem colStart={3} pt="10px">
                <Text>
                  ${savings}
                </Text>
              </GridItem>
            </Grid>
          </Box>
        </Stack>

        <Stack
          direction="row"
          alignItems="center"
          justifyContent="center"
          pt="1rem"
        >
          <Heading size="xl" ml="20px" mb="10px">
            Destinations
          </Heading>
        </Stack>

        <Flex mb="2rem" bg="gray.100" overflow="auto" pt="1rem" pb="1rem" justifyContent="center">
          {comps.map((item, index) => {
            return item.account.accountType === "SAVINGS" ? (
              <Text>done</Text>
            ) : (
              <>
                <Stack
                  direction="column"
                  alignContent="center"
                  alignItems="center"
                >
                  <Text>{item.destination.name}</Text>
                  <CircularProgress
                    value={item.account.balance}
                    color="green.400"
                    size="200px"
                  >
                    <CircularProgressLabel>
                      {(item.account.balance / item.destination.goal) * 100}%
                    </CircularProgressLabel>
                  </CircularProgress>
                </Stack>
              </>
            );
          })}
        </Flex>

        <Stack direction="row" justifyContent="space-around">
          <AddCompartment onAdd={addDest} />
          <TransferForm onAdd={Transfer} />
        </Stack>
      </Flex>
    </>

    // <Grid
    //   h="200px"
    //   templateRows="repeat(2, 1fr)"
    //   templateColumns="repeat(5, 1fr)"
    //   gap={8}
    //   paddingLeft={400}
    // >
    //   <GridItem>
    //     <div className="smallbox">
    //       Main savings total
    //       <Box>5349.34 $</Box>
    //     </div>
    //   </GridItem>
    //   <GridItem alignItems="center">
    //     <AddCompartment onAdd={addDest} />
    //     {/* form to add compartments and progress trackers */}
    //   </GridItem>
    //   <GridItem>
    //     <TransferForm onAdd={Transfer} />
    //   </GridItem>
    // </Grid>
    // <Grid
    //   h="200px"
    //   templateRows="repeat(2, 1fr)"
    //   templateColumns="repeat(5, 1fr)"
    //   gap={8}
    //   paddingLeft={300}
    // >
    //   <GridItem>
    //     {/* Mortgage
    //     <CircularProgress value={40} color="green.400" size="200px">
    //       <CircularProgressLabel>40%</CircularProgressLabel>
    //     </CircularProgress> */}
    //   </GridItem>
    //   <GridItem>
    //     {/* Gaming?
    //     <CircularProgress value={10} color="green.400" size="200px">
    //       <CircularProgressLabel>10%</CircularProgressLabel>
    //     </CircularProgress> */}
    //   </GridItem>
    //   <GridItem>
    //     {/* Savings
    //     <CircularProgress value={30} color="green.400" size="200px">
    //       <CircularProgressLabel>30%</CircularProgressLabel>
    //     </CircularProgress> */}
    //   </GridItem>
    //   <GridItem>
    //     {/* Uh idk
    //     <CircularProgress value={60} color="green.400" size="200px">
    //       <CircularProgressLabel>60%</CircularProgressLabel>
    //     </CircularProgress> */}
    //   </GridItem>
    // </Grid>
    // <Grid
    //   h="200px"
    //   templateRows="repeat(2, 1fr)"
    //   templateColumns="repeat(5, 1fr)"
    //   gap={8}
    //   paddingLeft={300}
    // >
    //   <GridItem>
    //     Mortgage
    //     <CircularProgress value={40} color="green.400" size="200px">
    //       <CircularProgressLabel>40%</CircularProgressLabel>
    //     </CircularProgress>
    //   </GridItem>
    //   <GridItem>
    //     Gaming?
    //     <CircularProgress value={10} color="green.400" size="200px">
    //       <CircularProgressLabel>10%</CircularProgressLabel>
    //     </CircularProgress>
    //   </GridItem>
    //   <GridItem>
    //     Savings
    //     <CircularProgress value={30} color="green.400" size="200px">
    //       <CircularProgressLabel>30%</CircularProgressLabel>
    //     </CircularProgress>
    //   </GridItem>
    //   <GridItem>
    //     Uh idk
    //     <CircularProgress value={60} color="green.400" size="200px">
    //       <CircularProgressLabel>60%</CircularProgressLabel>
    //     </CircularProgress>
    //   </GridItem>
    // </Grid>
    // <Flex style={{ paddingTop: 20, paddingRight: 40 }}>
    // {comps.map((item, index)  => (
    //   <>
    //     {item.account.accountType === "SAVINGS"? <></>:<> {item.destination.name}
    //     <CircularProgress
    //       value={item.destination.goal - item.destination.goal + item.account.balance}
    //       color="green.400"
    //       size="200px"
    //     >
    //       <CircularProgressLabel>{item.destination.goal - item.destination.goal + item.account.balance}%</CircularProgressLabel>
    //     </CircularProgress></>
    //   ))}
    // </Flex>
  );
};

export default FinancialView;
