use rand::Rng;

fn main() {
    let mut rng = rand::thread_rng();
    let mut clients_row = Vec::new();
    let mut client: u8 = 1;
    let mut decision: u8;
    let mut round: u8 = 0;
    clients_row.push(client);
    client += 1;
    while round <= 100{
        decision =  rng.gen_range(1..=3);
        if decision == 1{
            clients_row.push(client);
            client += 1
        } else if decision == 2{
            clients_row.pop();
        } else if decision == 3{
            clients_row.remove(0);
        }
        println!("$| {:?} |IN", clients_row);
        if clients_row.len() < 1{
            round = 100;
            println!("End");
        }
        round+=1;
    }
    println!("Hello, world!");
}