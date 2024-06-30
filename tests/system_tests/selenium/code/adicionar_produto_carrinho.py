from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

# como executar:
# 1 - possuir a pasta 'venv' dentro da pasta 'selenium'
# 2 - rodar venv\Scripts\activate na pasta 'selenium'
# 3 - entrar na pasta 'code' e rodar o script: python teste.py

def main():
    driver = webdriver.Chrome()
    
    teste_adicionar_produto_carrinho(driver)

    input("Terminou. Tecle enter para encerrar.")
    driver.quit()

def teste_adicionar_produto_carrinho(driver):

    driver.get("http://localhost:9000/")

    time.sleep(2)

    produto_xpath = "/html/body/div[2]/div/section[2]/div/div[2]/div[1]"

    try:
        produto_elemento = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, produto_xpath))
        )

        driver.execute_script("arguments[0].scrollIntoView();", produto_elemento)

        time.sleep(1)

        produto_elemento.click()
        
        print("Produto clicado")
        time.sleep(2)

        # clicar no botão de adicionar ao carrinho
        add_to_cart_xpath = "/html/body/div[2]/div/section[1]/div/div/div[2]/form/div/div/div[2]/button"
        add_to_cart_button = WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.XPATH, add_to_cart_xpath))
        )
        add_to_cart_button.click()
        print("Produto adicionado ao carrinho")

    except Exception as e:
        print(f"Erro durante a execução do teste: {e}")

    input('Toque para encerrar...')
    
if __name__ == "__main__":
    main()
